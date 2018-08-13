package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.SearchPhotoResult
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.SearchService
import com.github.jasonhezz.likesplash.ui.explore.SearchPhotoDataSourceFactory
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by JavaCoder on 2017/12/6.
 */
interface SearchRepository {

    fun searchPagePhotos(
        query: String,
        page: Int = 1, per_page: Int = 20
    ): Listing<Photo>

    fun searchPhotos(
        query: String,
        page: Int = 1, per_page: Int = 20
    ): LiveData<List<Photo>>

    fun searchPageCollections(
        query: String,
        page: Int = 1, per_page: Int = 20
    ): Single<List<Collection>>

    fun searchUsers(query: String, page: Int = 1, per_page: Int = 20): Single<List<User>>
}

class SearchRepositoryIml(private val service: SearchService) : SearchRepository {

    override fun searchPagePhotos(query: String, page: Int, per_page: Int): Listing<Photo> {
        val sourceFactory = SearchPhotoDataSourceFactory(query, service)
        val livePagedList = LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder().setInitialLoadSizeHint(per_page).setPageSize(per_page).build()
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

    override fun searchPhotos(query: String, page: Int, per_page: Int): LiveData<List<Photo>> {
        val result = MutableLiveData<List<Photo>>()
        service.searchPhotos(query, page, per_page).enqueue(object : Callback<SearchPhotoResult> {
            override fun onFailure(call: Call<SearchPhotoResult>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<SearchPhotoResult>?, response: Response<SearchPhotoResult>?) {
                response?.body()?.results?.let { result.value = it }
            }
        })
        return result
    }

    override fun searchPageCollections(
        query: String, page: Int,
        per_page: Int
    ): Single<List<Collection>> {
        return service.searchCollections(query, page, per_page)
    }

    override fun searchUsers(query: String, page: Int, per_page: Int): Single<List<User>> {
        return service.searchUsers(query, page, per_page)
    }
}