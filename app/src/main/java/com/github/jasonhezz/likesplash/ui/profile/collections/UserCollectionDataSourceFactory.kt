package com.github.jasonhezz.likesplash.ui.profile.collections

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.service.UserService

/**
 * Created by JavaCoder on 2017/12/12.
 */
class UserCollectionDataSourceFactory(
    private val userName: String,
    private val api: UserService
) : DataSource.Factory<Int, Collection>() {
    val sourceLiveData = MutableLiveData<PagedUserCollectionDataSource>()
    override fun create(): DataSource<Int, Collection> {
        val source = PagedUserCollectionDataSource(userName, api)
        sourceLiveData.postValue(source)
        return source
    }
}