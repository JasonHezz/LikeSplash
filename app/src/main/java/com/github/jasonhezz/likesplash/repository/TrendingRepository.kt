package com.github.jasonhezz.likesplash.repository

import com.github.jasonhezz.likesplash.data.TrendingFeed
import com.github.jasonhezz.likesplash.data.api.TrendingService
import io.reactivex.Single

/**
 * Created by JavaCoder on 2017/11/27.
 */
interface TrendingRepository {
  fun getTrendingFeed(after: String? = null): Single<TrendingFeed>

  fun getFollowingFeed(after: String? = null)

  fun followUser(username: String)

  fun unfollowUser(username: String)
}

class TrendingRepositoryIml(val trendingService: TrendingService) : TrendingRepository {
  override fun getTrendingFeed(after: String?): Single<TrendingFeed> =
      trendingService.getTrendingFeed(after)

  override fun getFollowingFeed(after: String?) = trendingService.getFollowingFeed(after)

  override fun followUser(username: String) {
    return trendingService.followUser(username)
  }

  override fun unfollowUser(username: String) {
    return trendingService.unfollowUser(username)
  }
}