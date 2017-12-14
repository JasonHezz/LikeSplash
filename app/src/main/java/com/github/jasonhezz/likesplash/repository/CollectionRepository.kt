package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.api.CollectionService
import com.github.jasonhezz.likesplash.ui.collection.CuratedCollectionDataSourceFactory
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/13.
 */
interface CollectionRepository {
  fun getListCollections(perPage: Int = 10): Listing<Collection>
}

class CollectionRepositoryIml(private val api: CollectionService,
    private val networkExecutor: Executor) : CollectionRepository {

  override fun getListCollections(perPage: Int): Listing<Collection> {
    val sourceFactory = CuratedCollectionDataSourceFactory(api, networkExecutor)
    val livePagedList = LivePagedListBuilder(sourceFactory, perPage)
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