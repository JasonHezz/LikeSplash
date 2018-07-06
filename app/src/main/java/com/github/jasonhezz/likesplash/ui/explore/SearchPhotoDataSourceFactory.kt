package com.github.jasonhezz.likesplash.ui.explore

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.api.SearchService
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/12.
 */
class SearchPhotoDataSourceFactory(
    private val query: String,
    private val api: SearchService,
    private val retryExecutor: Executor
) : DataSource.Factory<Int, Photo>() {
    val sourceLiveData = MutableLiveData<PagedSearchPhotoDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val source = PagedSearchPhotoDataSource(query, api, retryExecutor)
        sourceLiveData.postValue(source)
        return source
    }
}