package com.github.jasonhezz.likesplash.ui.collection.curated

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.service.CollectionService

/**
 * Created by JavaCoder on 2017/12/12.
 */
class CuratedCollectionDataSourceFactory(
        private val api: CollectionService
) : DataSource.Factory<Int, Collection>() {
    val sourceLiveData = MutableLiveData<PagedCuratedCollectionDataSource>()
    override fun create(): DataSource<Int, Collection> {
        val source = PagedCuratedCollectionDataSource(api)
        sourceLiveData.postValue(source)
        return source
    }
}