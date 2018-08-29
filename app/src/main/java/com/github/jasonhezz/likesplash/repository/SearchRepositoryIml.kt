package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.SearchPhotoResponse
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.data.service.SearchService
import com.github.jasonhezz.likesplash.ui.explore.SearchPhotoDataSourceFactory
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepositoryIml(private val service: SearchService) : SearchRepository {

    override fun searchPagePhotos(query: String, page: Int, perPage: Int): Listing<Photo> {
        val sourceFactory = SearchPhotoDataSourceFactory(query, service)
        val livePagedList = LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder().setInitialLoadSizeHint(perPage).setPageSize(perPage).build()
        )
            .build()
        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        return Listing(
            pagedList = livePagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.networkState
            },
            retry = {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            refreshState = refreshState
        )
    }

    override fun searchPhotos(query: String, page: Int, perPage: Int): LiveData<List<Photo>> {
        val result = MutableLiveData<List<Photo>>()
        service.searchPhotos(query, page, perPage).enqueue(object : Callback<SearchPhotoResponse> {
            override fun onFailure(call: Call<SearchPhotoResponse>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<SearchPhotoResponse>?, response: Response<SearchPhotoResponse>?) {
                response?.body()?.results?.let { result.value = it }
            }
        })
        return result
    }

    override fun searchPageCollections(
        query: String, page: Int,
        perPage: Int
    ): Single<List<Collection>> {
        return service.searchCollections(query, page, perPage)
    }

    override fun searchUsers(query: String, page: Int, perPage: Int): Single<List<User>> {
        return service.searchUsers(query, page, perPage)
    }
}