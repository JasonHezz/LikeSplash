package com.github.jasonhezz.likesplash.repository

import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.data.entities.Photo

/**
 * Created by JavaCoder on 2017/11/27.
 */
interface TrendingRepository {

    fun getTrendingFeed(after: String? = null, perPage: Int = 150): Listing<Photo>

    fun getFollowingFeed(after: String? = null)
}

