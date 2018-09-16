package com.github.jasonhezz.likesplash.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.service.TrendingService
import com.github.jasonhezz.likesplash.ui.home.trending.TrendingPhotoDataSourceFactory

class TrendingRepositoryIml(val trendingService: TrendingService) : TrendingRepository {

    override fun getFollowingFeed(after: String?) = trendingService.getFollowingFeed(after)

    override fun getTrendingFeed(after: String?, perPage: Int): Listing<Photo> {
        val sourceFactory = TrendingPhotoDataSourceFactory(trendingService)
        val livePagedList = LivePagedListBuilder(
                sourceFactory,
                PagedList.Config.Builder().setInitialLoadSizeHint(perPage).setPageSize(perPage).build()
        ).build()
        return Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState },
                retry = { sourceFactory.sourceLiveData.value?.retryAllFailed() },
                refresh = { sourceFactory.sourceLiveData.value?.invalidate() },
                refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.initialLoad }
        )
    }
}