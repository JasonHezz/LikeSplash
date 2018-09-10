package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.jasonhezz.likesplash.data.entities.*
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.service.SearchService
import com.github.jasonhezz.likesplash.ui.collection.featured.FeaturedCollectionDataSourceFactory
import com.github.jasonhezz.likesplash.ui.search.SearchCollectionDataSourceFactory
import com.github.jasonhezz.likesplash.ui.search.SearchPhotoDataSourceFactory
import com.github.jasonhezz.likesplash.ui.search.SearchUserDataSourceFactory
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRepositoryIml(private val service: SearchService) : SearchRepository {

    override fun searchPagePhotos(query: String, page: Int, perPage: Int): LiveData<Listing<Photo>> {
        val result = MutableLiveData<Listing<Photo>>()
        val sourceFactory = SearchPhotoDataSourceFactory(query, service)
        val livePagedList = LivePagedListBuilder(
                sourceFactory,
                PagedList.Config.Builder().setInitialLoadSizeHint(perPage).setPageSize(perPage).build()
        ).build()
        result.value = Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState },
                retry = { sourceFactory.sourceLiveData.value?.retryAllFailed() },
                refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
                refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.initialLoad })
        return result
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

    override fun searchPageCollections(query: String, page: Int, perPage: Int): LiveData<Listing<Collection>> {
        val result = MutableLiveData<Listing<Collection>>()

        val sourceFactory = SearchCollectionDataSourceFactory(query , service)
        val livePagedList = LivePagedListBuilder(
                sourceFactory,
                PagedList.Config.Builder().setInitialLoadSizeHint(perPage).setPageSize(perPage).build()
        ).build()
        result.value = Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState },
                retry = { sourceFactory.sourceLiveData.value?.retryAllFailed() },
                refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
                refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.initialLoad }
        )

        return result
    }

    override fun searchUsers(query: String, page: Int, perPage: Int): LiveData<Listing<User>> {
        val result = MutableLiveData<Listing<User>>()

        val sourceFactory = SearchUserDataSourceFactory(query , service)
        val livePagedList = LivePagedListBuilder(
                sourceFactory,
                PagedList.Config.Builder().setInitialLoadSizeHint(perPage).setPageSize(perPage).build()
        ).build()
        result.value = Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState },
                retry = { sourceFactory.sourceLiveData.value?.retryAllFailed() },
                refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
                refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.initialLoad }
        )

        return result
    }

    override fun autoComplete(query: String): LiveData<SearchHints> {
        val result = MutableLiveData<SearchHints>()
        service.autoComplete(query).enqueue(object : Callback<SearchHints> {
            override fun onFailure(call: Call<SearchHints>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<SearchHints>?, response: Response<SearchHints>?) {
                result.value = response?.body()
            }
        })
        return result
    }
}