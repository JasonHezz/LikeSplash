package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.api.CollectionService
import com.github.jasonhezz.likesplash.ui.collection.CollectionDetailDataSourceFactory
import com.github.jasonhezz.likesplash.ui.collection.CuratedCollectionDataSourceFactory
import com.github.jasonhezz.likesplash.ui.collection.FeaturedCollectionDataSourceFactory

/**
 * Created by JavaCoder on 2017/12/13.
 */
interface CollectionRepository {

    fun getListCuratedCollections(perPage: Int = 20): Listing<Collection>

    fun getListFeaturedCollections(perPage: Int = 20): Listing<Collection>

    fun getListPhotoCollections(id: String, perPage: Int = 20): Listing<Photo>
}

class CollectionRepositoryIml(
    private val api: CollectionService
) : CollectionRepository {

    override fun getListCuratedCollections(perPage: Int): Listing<Collection> {
        val sourceFactory = CuratedCollectionDataSourceFactory(api)
        val livePagedList = LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder().setInitialLoadSizeHint(perPage).setPageSize(perPage).build()
        ).build()
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

    override fun getListFeaturedCollections(perPage: Int): Listing<Collection> {
        val sourceFactory = FeaturedCollectionDataSourceFactory(api)
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

    override fun getListPhotoCollections(id: String, perPage: Int): Listing<Photo> {
        val sourceFactory = CollectionDetailDataSourceFactory(id, api)
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
}