package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.api.CollectionService
import com.github.jasonhezz.likesplash.ui.collection.CuratedCollectionDataSourceFactory
import com.github.jasonhezz.likesplash.ui.collection.FeaturedCollectionDataSourceFactory
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/13.
 */
interface CollectionRepository {

  fun getListCuratedCollections(perPage: Int = 10): Listing<Collection>

  fun getListFeaturedCollections(perPage: Int = 10): Listing<Collection>
}

class CollectionRepositoryIml(private val api: CollectionService,
    private val networkExecutor: Executor) : CollectionRepository {

  override fun getListCuratedCollections(perPage: Int): Listing<Collection> {
    val sourceFactory = CuratedCollectionDataSourceFactory(api, networkExecutor)
    val livePagedList = LivePagedListBuilder(sourceFactory,
        PagedList.Config.Builder().setInitialLoadSizeHint(perPage).setPageSize(perPage).build())
        // provide custom executor for network requests, otherwise it will default to
        // Arch Components' IO pool which is also used for disk access
        .setBackgroundThreadExecutor(networkExecutor)
        .build()
    val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
      it.initialLoad
    }
    return Listing(
        pagedList = livePagedList,
        networkState = Transformations.switchMap(sourceFactory.sourceLiveData, {
          it.networkState
        }),
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
    val sourceFactory = FeaturedCollectionDataSourceFactory(api, networkExecutor)
    val livePagedList = LivePagedListBuilder(sourceFactory,
        PagedList.Config.Builder().setInitialLoadSizeHint(perPage).setPageSize(perPage).build())
        // provide custom executor for network requests, otherwise it will default to
        // Arch Components' IO pool which is also used for disk access
        .setBackgroundThreadExecutor(networkExecutor)
        .build()
    val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
      it.initialLoad
    }
    return Listing(
        pagedList = livePagedList,
        networkState = Transformations.switchMap(sourceFactory.sourceLiveData, {
          it.networkState
        }),
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