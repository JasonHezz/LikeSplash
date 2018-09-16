package com.github.jasonhezz.likesplash.repository

import androidx.lifecycle.LiveData
import com.github.jasonhezz.likesplash.data.entities.DownLoadLink
import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.enumerations.OrderBy
import io.reactivex.Single

/**
 * Created by JavaCoder on 2017/11/27.
 */
interface PhotoRepository {

    fun getListPhotos(page: Int = 1, perPage: Int = 24, orderBy: OrderBy = OrderBy.LATEST): Listing<Photo>

    fun getListCuratedPhotos(page: Int = 1, perPage: Int = 24, orderBy: OrderBy = OrderBy.LATEST): Single<List<Photo>>

    fun getAPhoto(id: String, w: Int? = null, h: Int? = null): LiveData<Photo>

    fun getListRandomPhoto(
            collections: String? = null,
            featured: String? = null,
            username: String? = null,
            query: String? = null,
            orientation: String? = null,
            w: Int? = null,
            h: Int? = null,
            count: Int = 1
    ): Single<List<Photo>>

    fun getAPhotoStatistics(id: Int, resolution: String? = null, quantity: Int = 20)

    fun getAPhotoDownloadLink(id: String): Single<DownLoadLink>

    fun updateAPhoto(id: String)

    fun likeAPhoto(id: String)

    fun unlikeAPhoto(id: String)
}