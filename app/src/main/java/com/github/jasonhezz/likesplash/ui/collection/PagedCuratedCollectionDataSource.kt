package com.github.jasonhezz.likesplash.ui.collection

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.api.ApiResponse
import com.github.jasonhezz.likesplash.data.api.CollectionService
import com.github.jasonhezz.likesplash.data.api.Resource
import retrofit2.HttpException
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/12.
 */
class PagedCuratedCollectionDataSource(
    val api: CollectionService,
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
        try {
            val response = api.getListCuratedCollections(perPage = params.requestedLoadSize).execute()
            if (response.isSuccessful) {
                val apiResponse = ApiResponse(response)
                val items = apiResponse.body ?: emptyList()
                callback.onResult(items, apiResponse.prevPage, apiResponse.nextPage)
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
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Collection>) {
        networkState.postValue(Resource.MORE)
        try {
            val response = api.getListCuratedCollections(params.key, params.requestedLoadSize).execute()
            if (response.isSuccessful) {
                val apiResponse = ApiResponse(response)
                val items = apiResponse.body ?: emptyList()
                callback.onResult(items, apiResponse.nextPage)
                retry = null
            } else {
                retry = { loadAfter(params, callback) }
                networkState.postValue(
                    Resource.error("error code: ${response.code()}")
                )
            }
        } catch (e: HttpException) {
            networkState.postValue(Resource.error(e.message ?: "unknown err"))
            retry = { loadAfter(params, callback) }
        } finally {
            networkState.postValue(Resource.LOADED)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Collection>) {
        // ignored, since we only ever append to our initial load
    }
}