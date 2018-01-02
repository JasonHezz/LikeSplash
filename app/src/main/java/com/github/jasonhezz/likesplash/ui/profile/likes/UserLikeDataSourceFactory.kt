package com.github.jasonhezz.likesplash.ui.profile.likes

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.api.UserService
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/12.
 */
class UserLikeDataSourceFactory(private val userName: String,
    private val api: UserService,
    private val retryExecutor: Executor) : DataSource.Factory<Int, Photo> {
  val sourceLiveData = MutableLiveData<PagedUserLikeDataSource>()
  override fun create(): DataSource<Int, Photo> {
    val source = PagedUserLikeDataSource(userName, api, retryExecutor)
    sourceLiveData.postValue(source)
    return source
  }
}