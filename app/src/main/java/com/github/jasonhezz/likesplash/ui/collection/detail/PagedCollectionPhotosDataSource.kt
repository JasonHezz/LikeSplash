package com.github.jasonhezz.likesplash.ui.collection.detail

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.github.jasonhezz.likesplash.data.api.ApiResponse
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.service.CollectionService
import retrofit2.HttpException

/**
 * Created by JavaCoder on 2017/12/12.
 */
class PagedCollectionPhotosDataSource(
        val id: String,
        val api: CollectionService
) : PageKeyedDataSource<Int, Photo>() {

    private var retry: (() -> Any)? = null
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

    override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Photo>
    ) {
        networkState.postValue(Resource.INITIAL)
        initialLoad.postValue(Resource.INITIAL)
        try {
            val response = api.getCollectionPhotos(id, perPage = params.requestedLoadSize).execute()
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
        } catch (e: HttpException) {
            networkState.postValue(Resource.error(e.message ?: "unknown err"))
            retry = { loadInitial(params, callback) }
        } finally {
            networkState.postValue(Resource.LOADED)
            initialLoad.postValue(Resource.LOADED)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        networkState.postValue(Resource.MORE)
        try {
            val response = api.getCollectionPhotos(id, page = params.key, perPage = params.requestedLoadSize).execute()
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
        } catch (e: Exception) {
            networkState.postValue(Resource.error(e.message ?: "unknown err"))
            retry = { loadAfter(params, callback) }
        } finally {
            networkState.postValue(Resource.LOADED)
            initialLoad.postValue(Resource.LOADED)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        // ignored, since we only ever append to our initial load
    }
}