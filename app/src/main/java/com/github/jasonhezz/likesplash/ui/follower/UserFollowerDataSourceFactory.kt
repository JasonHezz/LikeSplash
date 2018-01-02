package com.github.jasonhezz.likesplash.ui.follower

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.UserService
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/12.
 */
class UserFollowerDataSourceFactory(private val userName: String,
    private val api: UserService,
    private val retryExecutor: Executor) : DataSource.Factory<Int, User> {
  val sourceLiveData = MutableLiveData<PagedUserFollowerDataSource>()
  override fun create(): DataSource<Int, User> {
    val source = PagedUserFollowerDataSource(userName, api, retryExecutor)
    sourceLiveData.postValue(source)
    return source
  }
}