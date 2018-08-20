package com.github.jasonhezz.likesplash.repository

import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.data.enumerations.OrderBy
import io.reactivex.Single

/**
 * Created by JavaCoder on 2017/12/1.
 */
interface UserRepository {

    fun getUserProfile(username: String, w: Int? = null, h: Int? = null): Single<User>

    fun getUserFollowing(
        username: String,
        page: Int = 1,
        perPage: Int = 20
    ): Listing<User>

    fun getUserFollowers(
        username: String,
        page: Int = 1,
        perPage: Int = 20
    ): Listing<User>

    fun getUserLikes(
        username: String,
        page: Int = 1,
        perPage: Int = 20,
        orderBy: OrderBy = OrderBy.LATEST
    ): Listing<Photo>

    fun getUserPhotos(
        username: String,
        page: Int = 1,
        perPage: Int = 10,
        orderBy: OrderBy = OrderBy.LATEST
    ): Listing<Photo>

    fun getUserCollection(
        username: String,
        page: Int = 1,
        perPage: Int = 20,
        orderBy: OrderBy = OrderBy.LATEST
    ): Listing<Collection>

    fun getUserStatistics(
        username: String, resolution: String? = null,
        quantity: Int = 30
    )

    fun followUser(username: String)

    fun unfollowUser(username: String)
}

