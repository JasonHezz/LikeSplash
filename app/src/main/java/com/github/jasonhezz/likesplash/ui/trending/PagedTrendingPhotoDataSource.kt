package com.github.jasonhezz.likesplash.ui.trending

import android.annotation.SuppressLint
import android.arch.core.executor.ArchTaskExecutor
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.net.Uri
import com.github.jasonhezz.likesplash.data.TrendingFeed
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.service.TrendingService
import retrofit2.Call
import retrofit2.Response

/**
 * Created by JavaCoder on 2017/12/12.
 */
class PagedTrendingPhotoDataSource(
    val api: TrendingService
) : PageKeyedDataSource<String, Photo>() {

    // keep a function reference for the retry event
    private var retry: (() -> Any)? = null
    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter.
     */
    val networkState = MutableLiveData<Resource>()
    val initialLoad = MutableLiveData<Resource>()

    @SuppressLint("RestrictedApi")
    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            ArchTaskExecutor.getIOThreadExecutor().execute {
                it.invoke()
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Photo>) {
        networkState.postValue(Resource.MORE)
        api.getTrendingFeed(params.key, per_page = params.requestedLoadSize)
            .enqueue(object : retrofit2.Callback<TrendingFeed> {
                override fun onFailure(call: Call<TrendingFeed>?, t: Throwable?) {
                    retry = {
                        loadAfter(params, callback)
                    }
                    networkState.postValue(Resource.LOADED)
                    initialLoad.postValue(Resource.LOADED)
                    networkState.postValue(Resource.error(t?.message ?: "unknown err"))
                }

                override fun onResponse(call: Call<TrendingFeed>?, response: Response<TrendingFeed>) {
                    if (response.isSuccessful) {
                        val uri = Uri.parse(response.body()?.next_page)
                        val page = uri.getQueryParameter("after")
                        retry = null
                        networkState.postValue(Resource.LOADED)
                        initialLoad.postValue(Resource.LOADED)
                        callback.onResult(response.body()?.photos ?: emptyList(), page)
                    } else {
                        networkState.postValue(Resource.LOADED)
                        initialLoad.postValue(Resource.LOADED)
                        retry = {
                            loadAfter(params, callback)
                        }
                        networkState.postValue(
                            Resource.error("error code: ${response.code()}")
                        )
                    }
                }
            })
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Photo>) {
        networkState.postValue(Resource.INITIAL)
        initialLoad.postValue(Resource.INITIAL)
        try {
            val response = api.getTrendingFeed(per_page = params.requestedLoadSize).execute()
            if (response.isSuccessful) {
                val uri = Uri.parse(response.body()?.next_page)
                val items = response.body()?.photos ?: emptyList()
                val page = uri.getQueryParameter("after")
                callback.onResult(items, null, page)
                retry = null
            } else {
                retry = { loadInitial(params, callback) }
                networkState.postValue(
                    Resource.error("error code: ${response.code()}")
                )
            }
        } catch (e: Exception) {
            networkState.postValue(Resource.error(e.message ?: "unknown err"))
            retry = { loadInitial(params, callback) }
        } finally {
            networkState.postValue(Resource.LOADED)
            initialLoad.postValue(Resource.LOADED)
        }
        /*networkState.postValue(Resource.INITIAL)
        initialLoad.postValue(Resource.INITIAL)
        api.getTrendingFeed(per_page = params.requestedLoadSize).enqueue(object : retrofit2.Callback<TrendingFeed> {
            override fun onFailure(call: Call<TrendingFeed>?, t: Throwable?) {
                retry = {
                    loadInitial(params, callback)
                }
                networkState.postValue(Resource.LOADED)
                initialLoad.postValue(Resource.LOADED)
                networkState.postValue(Resource.error(t?.message ?: "unknown err"))
            }

            override fun onResponse(call: Call<TrendingFeed>?, response: Response<TrendingFeed>) {
                if (response.isSuccessful) {
                    val uri = Uri.parse(response.body()?.next_page)
                    val items = response.body()?.photos ?: emptyList()
                    val page = uri.getQueryParameter("after")
                    retry = null
                    networkState.postValue(Resource.LOADED)
                    initialLoad.postValue(Resource.LOADED)
                    callback.onResult(items, "", page)
                } else {
                    networkState.postValue(Resource.LOADED)
                    initialLoad.postValue(Resource.LOADED)
                    retry = {
                        loadInitial(params, callback)
                    }
                    networkState.postValue(
                        Resource.error("error code: ${response.code()}")
                    )
                }
            }
        })*/
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Photo>) {
        // ignored, since we only ever append to our initial load
    }
}