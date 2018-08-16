package com.github.jasonhezz.likesplash.ui.profile.photos

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.service.UserService

/**
 * Created by JavaCoder on 2017/12/12.
 */
class UserPhotoDataSourceFactory(
    private val userName: String,
    private val api: UserService
) : DataSource.Factory<Int, Photo>() {
    val sourceLiveData = MutableLiveData<PagedUserPhotoDataSource>()
    override fun create(): DataSource<Int, Photo> {
        val source = PagedUserPhotoDataSource(userName, api)
        sourceLiveData.postValue(source)
        return source
    }
}