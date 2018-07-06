package com.github.jasonhezz.likesplash.ui.profile.collections

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.api.ApiResponse
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.UserService
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/12.
 */
class PagedUserCollectionDataSource(
    private val userName: String,
    val api: UserService,
    private val retryExecutor: Executor
) : PageKeyedDataSource<Int, Collection>() {

    // keep a function reference for the retry event
    private var retry: (() -> Any)? = null
    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter.
     */
    val networkState = MutableLiveData<Resource>()
    val initialLoad = MutableLiveData<Resource>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Collection>
    ) {
        networkState.postValue(Resource.INITIAL)
        initialLoad.postValue(Resource.INITIAL)
        api.getUserCollection(userName, 1, params.requestedLoadSize).enqueue(
            object : retrofit2.Callback<List<Collection>> {
                override fun onFailure(call: Call<List<Collection>>, t: Throwable) {
                    retry = {
                        loadInitial(params, callback)
                    }
                    networkState.postValue(Resource.LOADED)
                    initialLoad.postValue(Resource.LOADED)
                    networkState.postValue(Resource.error(t.message ?: "unknown err"))
                }

                override fun onResponse(
                    call: Call<List<Collection>>,
                    response: Response<List<Collection>>
                ) {
                    if (response.isSuccessful) {
                        val apiResponse = ApiResponse(response)
                        val items = apiResponse.body ?: emptyList()
                        retry = null
                        networkState.postValue(Resource.LOADED)
                        initialLoad.postValue(Resource.LOADED)
                        callback.onResult(items, apiResponse.prevPage, apiResponse.nextPage)
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
            }
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Collection>) {
        networkState.postValue(Resource.MORE)
        api.getUserCollection(userName, params.key, params.requestedLoadSize).enqueue(
            object : retrofit2.Callback<List<Collection>> {
                override fun onFailure(call: Call<List<Collection>>, t: Throwable) {
                    retry = {
                        loadAfter(params, callback)
                    }
                    networkState.postValue(Resource.error(t.message ?: "unknown err"))
                }

                override fun onResponse(
                    call: Call<List<Collection>>,
                    response: Response<List<Collection>>
                ) {
                    if (response.isSuccessful) {
                        val apiResponse = ApiResponse(response)
                        val items = apiResponse.body ?: emptyList()
                        retry = null
                        networkState.postValue(Resource.LOADED)
                        callback.onResult(items, apiResponse.nextPage)
                    } else {
                        networkState.postValue(Resource.LOADED)
                        retry = {
                            loadAfter(params, callback)
                        }
                        networkState.postValue(
                            Resource.error("error code: ${response.code()}")
                        )
                    }
                }
            }
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Collection>) {
        // ignored, since we only ever append to our initial load
    }
}