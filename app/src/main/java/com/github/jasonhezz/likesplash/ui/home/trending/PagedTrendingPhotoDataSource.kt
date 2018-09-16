package com.github.jasonhezz.likesplash.ui.home.trending

import android.annotation.SuppressLint
import android.net.Uri
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.TrendingResponse
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
        api.getTrendingFeed(params.key, perPage = params.requestedLoadSize)
                .enqueue(object : retrofit2.Callback<TrendingResponse> {
                    override fun onFailure(call: Call<TrendingResponse>?, t: Throwable?) {
                        retry = {
                            loadAfter(params, callback)
                        }
                        networkState.postValue(Resource.LOADED)
                        initialLoad.postValue(Resource.LOADED)
                        networkState.postValue(Resource.error(t?.message ?: "unknown err"))
                    }

                    override fun onResponse(call: Call<TrendingResponse>?, response: Response<TrendingResponse>) {
                        if (response.isSuccessful) {
                            val nextPage = response.body()?.nextPage
                            var parsePage: String? = null
                            if (nextPage != null) {
                                val uri = Uri.parse(response.body()?.nextPage)
                                parsePage = uri.getQueryParameter("after")
                            }
                            retry = null
                            networkState.postValue(Resource.LOADED)
                            initialLoad.postValue(Resource.LOADED)
                            callback.onResult(response.body()?.photos ?: emptyList(), parsePage)
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
            val response = api.getTrendingFeed(perPage = params.requestedLoadSize).execute()
            if (response.isSuccessful) {
                val uri = Uri.parse(response.body()?.nextPage)
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
        api.getTrendingFeed(per_page = params.requestedLoadSize).enqueue(object : retrofit2.Callback<TrendingResponse> {
            override fun onFailure(call: Call<TrendingResponse>?, t: Throwable?) {
                retry = {
                    loadInitial(params, callback)
                }
                networkState.postValue(Resource.LOADED)
                initialLoad.postValue(Resource.LOADED)
                networkState.postValue(Resource.error(t?.message ?: "unknown err"))
            }

            override fun onResponse(call: Call<TrendingResponse>?, response: Response<TrendingResponse>) {
                if (response.isSuccessful) {
                    val uri = Uri.parse(response.body()?.nextPage)
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