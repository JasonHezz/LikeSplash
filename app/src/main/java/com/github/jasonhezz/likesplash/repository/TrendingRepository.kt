package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.TrendingFeed
import com.github.jasonhezz.likesplash.data.api.TrendingService
import com.github.jasonhezz.likesplash.ui.trending.TrendingPhotoDataSourceFactory
import io.reactivex.Single

/**
 * Created by JavaCoder on 2017/11/27.
 */
interface TrendingRepository {
    fun getTrendingFeed(after: String? = null): Single<TrendingFeed>

    fun getTrendingFeed(after: String? = null, perPage: Int = 20): Listing<Photo>

    fun getFollowingFeed(after: String? = null)
}

class TrendingRepositoryIml(val trendingService: TrendingService) : TrendingRepository {
    override fun getTrendingFeed(after: String?): Single<TrendingFeed> =
        trendingService.getTrendingFeed(after)

    override fun getFollowingFeed(after: String?) = trendingService.getFollowingFeed(after)

    override fun getTrendingFeed(after: String?, perPage: Int): Listing<Photo> {
        val sourceFactory = TrendingPhotoDataSourceFactory(trendingService)
        val livePagedList = LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder().setInitialLoadSizeHint(perPage).setPageSize(perPage).build()
        ).build()
        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        return Listing(
            pagedList = livePagedList,
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.networkState
            },
            retry = {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            refreshState = refreshState
        )
    }
}