package com.github.jasonhezz.likesplash.repository

import com.github.jasonhezz.likesplash.data.TrendingFeed
import com.github.jasonhezz.likesplash.data.entities.Photo
import io.reactivex.Single

/**
 * Created by JavaCoder on 2017/11/27.
 */
interface TrendingRepository {
    fun getTrendingFeed(after: String? = null): Single<TrendingFeed>

    fun getTrendingFeed(after: String? = null, perPage: Int = 20): Listing<Photo>

    fun getFollowingFeed(after: String? = null)
}

