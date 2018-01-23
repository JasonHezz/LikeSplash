package com.github.jasonhezz.likesplash.ui.collection

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.api.CollectionService
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/12.
 */
class FeaturedCollectionDataSourceFactory(
    private val api: CollectionService,
    private val retryExecutor: Executor) : DataSource.Factory<Int, Collection> {
  val sourceLiveData = MutableLiveData<PagedFeaturedCollectionDataSource>()
  override fun create(): DataSource<Int, Collection> {
    val source = PagedFeaturedCollectionDataSource(api, retryExecutor)
    sourceLiveData.postValue(source)
    return source
  }
}