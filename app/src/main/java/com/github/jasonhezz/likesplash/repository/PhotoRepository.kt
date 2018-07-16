package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.jasonhezz.likesplash.data.DownLoadLink
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.api.DAYS
import com.github.jasonhezz.likesplash.data.api.LATEST
import com.github.jasonhezz.likesplash.data.api.PhotoService
import com.github.jasonhezz.likesplash.ui.timeline.TimelinePhotoDataSourceFactory
import io.reactivex.Single
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/11/27.
 */
interface PhotoRepository {

  fun getListPhotos(page: Int = 1, perPage: Int = 20,
      orderBy: String = LATEST): Listing<Photo>

  fun getListCuratedPhotos(page: Int = 1, perPage: Int = 20,
      orderBy: String = LATEST): Single<List<Photo>>

  fun getAPhoto(id: String, w: Int? = null, h: Int? = null):
      Single<Photo>

  fun getListRandomPhoto(collections: String? = null,
      featured: String? = null,
      username: String? = null,
      query: String? = null,
      orientation: String? = null,
      w: Int? = null,
      h: Int? = null,
      count: Int = 1): Single<List<Photo>>

  fun getAPhotoStatistics(id: Int, resolution: String = DAYS, quantity: Int = 30)

  fun getAPhotoDownloadLink(id: String): Single<DownLoadLink>

  fun updateAPhoto(id: String)

  fun likeAPhoto(id: String)

  fun unlikeAPhoto(id: String)
}

class PhotoRepositoryIml(val service: PhotoService,
    private val networkExecutor: Executor) : PhotoRepository {
  override fun getListPhotos(page: Int, perPage: Int,
      orderBy: String): Listing<Photo> {
    val sourceFactory = TimelinePhotoDataSourceFactory(service, networkExecutor)
    val livePagedList = LivePagedListBuilder(sourceFactory,
        PagedList.Config.Builder().setInitialLoadSizeHint(perPage).setPageSize(perPage).build())
        // provide custom executor for network requests, otherwise it will default to
        // Arch Components' IO pool which is also used for disk access
        .setFetchExecutor(networkExecutor)
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


  override fun getListCuratedPhotos(page: Int, perPage: Int,
      orderBy: String): Single<List<Photo>> =
      service.getListCuratedPhotos(page, perPage,
          orderBy)

  override fun getAPhoto(id: String, w: Int?, h: Int?): Single<Photo> =
      service.getAPhoto(id, w, h)

  override fun getListRandomPhoto(collections: String?, featured: String?, username: String?,
      query: String?, orientation: String?, w: Int?, h: Int?,
      count: Int): Single<List<Photo>> =
      service.getListRandomPhoto(collections, featured,
          username, query, orientation, w, h, count)

  override fun getAPhotoStatistics(id: Int, resolution: String, quantity: Int) =
      service.getAPhotoStatistics(id, resolution, quantity)

  override fun getAPhotoDownloadLink(id: String): Single<DownLoadLink> =
      service.getAPhotoDownloadLink(id)

  override fun updateAPhoto(id: String) = service.updateAPhoto(id)

  override fun likeAPhoto(id: String) {
    service.likeAPhoto(id)
  }

  override fun unlikeAPhoto(id: String) = service.unlikeAPhoto(id)
}