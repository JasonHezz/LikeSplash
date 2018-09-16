package com.github.jasonhezz.likesplash.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.service.SearchService
import com.github.jasonhezz.likesplash.ui.explore.PagedSearchPhotoDataSource

/**
 * Created by JavaCoder on 2017/12/12.
 */
class SearchPhotoDataSourceFactory(
        private val query: String,
        private val api: SearchService
) : DataSource.Factory<Int, Photo>() {
    val sourceLiveData = MutableLiveData<PagedSearchPhotoDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val source = PagedSearchPhotoDataSource(query, api)
        sourceLiveData.postValue(source)
        return source
    }
}