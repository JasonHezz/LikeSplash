package com.github.jasonhezz.likesplash.ui.collection.curated

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.service.CollectionService
import com.github.jasonhezz.likesplash.ui.collection.detail.PagedCuratedCollectionPhotosDataSource

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