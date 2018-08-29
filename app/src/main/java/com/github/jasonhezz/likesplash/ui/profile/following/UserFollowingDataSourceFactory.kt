package com.github.jasonhezz.likesplash.ui.profile.following

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.data.service.UserService

/**
 * Created by JavaCoder on 2017/12/12.
 */
class UserFollowingDataSourceFactory(
    private val userName: String,
    private val api: UserService
) : DataSource.Factory<Int, User>() {
    val sourceLiveData = MutableLiveData<PagedUserFollowingDataSource>()
    override fun create(): DataSource<Int, User> {
        val source = PagedUserFollowingDataSource(userName, api)
        sourceLiveData.postValue(source)
        return source
    }
}