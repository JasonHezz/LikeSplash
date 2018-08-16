package com.github.jasonhezz.likesplash.ui.collection

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.service.CollectionService

/**
 * Created by JavaCoder on 2017/12/12.
 */
class CuratedCollectionPhotosDataSourceFactory(
    private val id: String,
    private val api: CollectionService
) : DataSource.Factory<Int, Photo>() {
    val sourceLiveData = MutableLiveData<PagedCuratedCollectionPhotosDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val source = PagedCuratedCollectionPhotosDataSource(id, api)
        sourceLiveData.postValue(source)
        return source
    }
}