package com.github.jasonhezz.likesplash.ui.home.trending

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.service.TrendingService

/**
 * Created by JavaCoder on 2017/12/12.
 */
class TrendingPhotoDataSourceFactory(
    private val api: TrendingService
) : DataSource.Factory<String, Photo>() {
    val sourceLiveData = MutableLiveData<PagedTrendingPhotoDataSource>()
    override fun create(): DataSource<String, Photo> {
        val source = PagedTrendingPhotoDataSource(api)
        sourceLiveData.postValue(source)
        return source
    }
}