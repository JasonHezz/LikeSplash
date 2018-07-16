package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.DAYS
import com.github.jasonhezz.likesplash.data.api.LATEST
import com.github.jasonhezz.likesplash.data.api.UserService
import com.github.jasonhezz.likesplash.ui.follower.UserFollowerDataSourceFactory
import com.github.jasonhezz.likesplash.ui.following.UserFollowingDataSourceFactory
import com.github.jasonhezz.likesplash.ui.profile.collections.UserCollectionDataSourceFactory
import com.github.jasonhezz.likesplash.ui.profile.likes.UserLikeDataSourceFactory
import com.github.jasonhezz.likesplash.ui.profile.photos.UserPhotoDataSourceFactory
import io.reactivex.Single
import java.util.concurrent.Executor

/**
 * Created by JavaCoder on 2017/12/1.
 */
interface UserRepository {

    fun getUserProfile(username: String, w: Int? = null, h: Int? = null): Single<User>

    fun getUserFollowing(
        username: String,
        page: Int = 1,
        per_page: Int = 20
    ): Listing<User>

    fun getUserFollowers(
        username: String,
        page: Int = 1,
        per_page: Int = 20
    ): Listing<User>

    fun getUserLikes(
        username: String,
        page: Int = 1,
        per_page: Int = 20,
        orderBy: String = LATEST
    ): Listing<Photo>

    fun getUserPhotos(
        username: String,
        page: Int = 1,
        per_page: Int = 10,
        orderBy: String = LATEST
    ): Listing<Photo>

    fun getUserCollection(
        username: String,
        page: Int = 1,
        per_page: Int = 20,
        orderBy: String = LATEST
    ): Listing<Collection>

    fun getUserStatistics(
        username: String, resolution: String = DAYS,
        quantity: Int = 30
    )

    fun followUser(username: String)

    fun unfollowUser(username: String)
}

class UserRepositoryIml(
    private val service: UserService,
    private val networkExecutor: Executor
) : UserRepository {
    override fun getUserProfile(username: String, w: Int?, h: Int?): Single<User> {
        return service.getUserProfile(username, w, h)
    }

    override fun getUserFollowing(username: String, page: Int, per_page: Int): Listing<User> {
        val sourceFactory = UserFollowingDataSourceFactory(username, service, networkExecutor)
        val livePagedList = LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder().setInitialLoadSizeHint(per_page).setPageSize(per_page).build()
        )
            .setFetchExecutor(networkExecutor)
            .build()
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

    override fun getUserFollowers(username: String, page: Int, per_page: Int): Listing<User> {
        val sourceFactory = UserFollowerDataSourceFactory(username, service, networkExecutor)
        val livePagedList = LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder().setInitialLoadSizeHint(per_page).setPageSize(per_page).build()
        )
            .setFetchExecutor(networkExecutor)
            .build()
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

    override fun getUserLikes(
        username: String, page: Int, per_page: Int,
        orderBy: String
    ): Listing<Photo> {
        val sourceFactory = UserLikeDataSourceFactory(username, service, networkExecutor)
        val livePagedList = LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder().setInitialLoadSizeHint(per_page).setPageSize(per_page).build()
        )
            .setFetchExecutor(networkExecutor)
            .build()
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

    override fun getUserPhotos(
        username: String, page: Int, per_page: Int,
        orderBy: String
    ): Listing<Photo> {
        val sourceFactory = UserPhotoDataSourceFactory(username, service, networkExecutor)
        val livePagedList = LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder().setInitialLoadSizeHint(per_page).setPageSize(per_page).build()
        )
            .setFetchExecutor(networkExecutor)
            .build()
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

    override fun getUserCollection(
        username: String, page: Int, per_page: Int,
        orderBy: String
    ): Listing<Collection> {
        val sourceFactory = UserCollectionDataSourceFactory(username, service, networkExecutor)
        val livePagedList = LivePagedListBuilder(
            sourceFactory,
            PagedList.Config.Builder().setInitialLoadSizeHint(per_page).setPageSize(per_page).build()
        )
            .setFetchExecutor(networkExecutor)
            .build()
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

    override fun getUserStatistics(username: String, resolution: String, quantity: Int) {
        return service.getUserStatistics(username, resolution, quantity)
    }

    override fun followUser(username: String) {
        return service.followUser(username)
    }

    override fun unfollowUser(username: String) {
        return service.unfollowUser(username)
    }
}