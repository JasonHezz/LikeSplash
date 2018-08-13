package com.github.jasonhezz.likesplash.ui.collection

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.api.CollectionService
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/12.
 */
class CollectionDetailDataSourceFactory(
    private val id: String,
    private val api: CollectionService
) : DataSource.Factory<Int, Photo>() {
    val sourceLiveData = MutableLiveData<PagedCollectionDetailDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val source = PagedCollectionDetailDataSource(id, api)
        sourceLiveData.postValue(source)
        return source
    }
}