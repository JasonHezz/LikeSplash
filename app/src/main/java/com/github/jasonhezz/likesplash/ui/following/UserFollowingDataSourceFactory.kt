package com.github.jasonhezz.likesplash.ui.following

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.UserService
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/12.
 */
class UserFollowingDataSourceFactory(
    private val userName: String,
    private val api: UserService,
    private val retryExecutor: Executor
) : DataSource.Factory<Int, User>() {
    val sourceLiveData = MutableLiveData<PagedUserFollowingDataSource>()
    override fun create(): DataSource<Int, User> {
        val source = PagedUserFollowingDataSource(userName, api, retryExecutor)
        sourceLiveData.postValue(source)
        return source
    }
}