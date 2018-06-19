package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.SearchService
import com.github.jasonhezz.likesplash.ui.explore.SearchPhotoDataSourceFactory
import io.reactivex.Single
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/6.
 */
interface SearchRepository {

  fun searchPhotos(query: String,
      page: Int = 1, per_page: Int = 10): Listing<Photo>

  fun searchCollections(query: String,
      page: Int = 1, per_page: Int = 10): Single<List<Collection>>

  fun searchUsers(query: String, page: Int = 1, per_page: Int = 10): Single<List<User>>
}

class SearchRepositoryIml(private val service: SearchService,
    private val networkExecutor: Executor) : SearchRepository {

  override fun searchPhotos(query: String, page: Int, per_page: Int): Listing<Photo> {
    val sourceFactory = SearchPhotoDataSourceFactory(query, service, networkExecutor)
    val livePagedList = LivePagedListBuilder(sourceFactory,
        PagedList.Config.Builder().setInitialLoadSizeHint(per_page).setPageSize(per_page).build())
        // provide custom executor for network requests, otherwise it will default to
        // Arch Components' IO pool which is also used for disk access
        .setFetchExecutor(networkExecutor)
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

  override fun searchCollections(query: String, page: Int,
      per_page: Int): Single<List<Collection>> {
    return service.searchCollections(query, page, per_page)
  }

  override fun searchUsers(query: String, page: Int, per_page: Int): Single<List<User>> {
    return service.searchUsers(query, page, per_page)
  }
}