package com.github.jasonhezz.likesplash.ui.editorial

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.service.PhotoService

/**
 * Created by JavaCoder on 2017/12/12.
 */
class EditorialPhotoDataSourceFactory(
    private val api: PhotoService
) : DataSource.Factory<Int, Photo>() {
    val sourceLiveData = MutableLiveData<PagedEditorialPhotoDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val source = PagedEditorialPhotoDataSource(api)
        sourceLiveData.postValue(source)
        return source
    }
}