package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.jasonhezz.likesplash.data.entities.DownLoadLink
import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.enumerations.OrderBy
import com.github.jasonhezz.likesplash.data.service.PhotoService
import com.github.jasonhezz.likesplash.ui.home.editorial.EditorialPhotoDataSourceFactory
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class PhotoRepositoryIml(val service: PhotoService) : PhotoRepository {
    override fun getListPhotos(
        page: Int, perPage: Int,
        orderBy: OrderBy
    ): Listing<Photo> {
        val sourceFactory = EditorialPhotoDataSourceFactory(service)
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

    override fun getListCuratedPhotos(
        page: Int, perPage: Int,
        orderBy: OrderBy
    ): Single<List<Photo>> =
        service.getListCuratedPhotos(
            page, perPage,
            orderBy
        )

    override fun getAPhoto(id: String, w: Int?, h: Int?): LiveData<Photo> {
        val result = MutableLiveData<Photo>()
        service.getAPhoto(id, w, h).enqueue(object : Callback<Photo> {
            override fun onFailure(call: Call<Photo>, t: Throwable) {
                Timber.d(t)
            }

            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
                response.body()?.let {
                    result.postValue(it)
                }
            }
        })
        return result
    }


    override fun getListRandomPhoto(
        collections: String?, featured: String?, username: String?,
        query: String?, orientation: String?, w: Int?, h: Int?,
        count: Int
    ): Single<List<Photo>> =
        service.getListRandomPhoto(
            collections, featured,
            username, query, orientation, w, h, count
        )

    override fun getAPhotoStatistics(id: Int, resolution: String?, quantity: Int) =
        service.getAPhotoStatistics(id, resolution, quantity)

    override fun getAPhotoDownloadLink(id: String): Single<DownLoadLink> =
        service.getAPhotoDownloadLink(id)

    override fun updateAPhoto(id: String) = service.updateAPhoto(id)

    override fun likeAPhoto(id: String) {
        service.likeAPhoto(id)
    }

    override fun unlikeAPhoto(id: String) = service.unlikeAPhoto(id)
}