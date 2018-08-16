package com.github.jasonhezz.likesplash.ui.collection

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.service.CollectionService

/**
 * Created by JavaCoder on 2017/12/12.
 */
class FeaturedCollectionDataSourceFactory(
    private val api: CollectionService
) : DataSource.Factory<Int, Collection>() {
    val sourceLiveData = MutableLiveData<PagedFeaturedCollectionDataSource>()
    override fun create(): DataSource<Int, Collection> {
        val source = PagedFeaturedCollectionDataSource(api)
        sourceLiveData.postValue(source)
        return source
    }
}