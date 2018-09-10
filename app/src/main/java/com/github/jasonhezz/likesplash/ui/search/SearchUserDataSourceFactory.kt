package com.github.jasonhezz.likesplash.ui.search

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.data.service.SearchService
import com.github.jasonhezz.likesplash.ui.profile.follower.PagedSearchUserDataSource

/**
 * Created by JavaCoder on 2017/12/12.
 */
class SearchUserDataSourceFactory(
    private val query: String,
    private val api: SearchService
) : DataSource.Factory<Int, User>() {
    val sourceLiveData = MutableLiveData<PagedSearchUserDataSource>()
    override fun create(): DataSource<Int, User> {
        val source = PagedSearchUserDataSource(query, api)
        sourceLiveData.postValue(source)
        return source
    }
}