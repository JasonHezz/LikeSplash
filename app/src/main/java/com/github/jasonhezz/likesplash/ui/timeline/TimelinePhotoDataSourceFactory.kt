package com.github.jasonhezz.likesplash.ui.timeline

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.api.PhotoService
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/12.
 */
class TimelinePhotoDataSourceFactory(
    private val api: PhotoService,
    private val retryExecutor: Executor) : DataSource.Factory<Int, Photo>() {
  val sourceLiveData = MutableLiveData<PagedTimelinePhotoDataSource>()
  override fun create(): DataSource<Int, Photo> {
    val source = PagedTimelinePhotoDataSource(api, retryExecutor)
    sourceLiveData.postValue(source)
    return source
  }
}