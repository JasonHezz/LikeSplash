package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.data.enumerations.OrderBy
import com.github.jasonhezz.likesplash.data.service.UserService
import com.github.jasonhezz.likesplash.ui.follower.UserFollowerDataSourceFactory
import com.github.jasonhezz.likesplash.ui.following.UserFollowingDataSourceFactory
import com.github.jasonhezz.likesplash.ui.profile.collections.UserCollectionDataSourceFactory
import com.github.jasonhezz.likesplash.ui.profile.likes.UserLikeDataSourceFactory
import com.github.jasonhezz.likesplash.ui.profile.photos.UserPhotoDataSourceFactory
import io.reactivex.Single

class UserRepositoryIml(
    private val service: UserService
) : UserRepository {
    override fun getUserProfile(username: String, w: Int?, h: Int?): Single<User> {
        return service.getUserProfile(username, w, h)
    }

    override fun getUserFollowing(username: String, page: Int, perPage: Int): Listing<User> {
        val sourceFactory = UserFollowingDataSourceFactory(username, service)
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

    override fun getUserFollowers(username: String, page: Int, perPage: Int): Listing<User> {
        val sourceFactory = UserFollowerDataSourceFactory(username, service)
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

    override fun getUserLikes(
        username: String, page: Int, perPage: Int,
        orderBy: OrderBy
    ): Listing<Photo> {
        val sourceFactory = UserLikeDataSourceFactory(username, service)
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

    override fun getUserPhotos(
        username: String, page: Int, perPage: Int,
        orderBy: OrderBy
    ): Listing<Photo> {
        val sourceFactory = UserPhotoDataSourceFactory(username, service)
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

    override fun getUserCollection(
        username: String, page: Int, perPage: Int,
        orderBy: OrderBy
    ): Listing<Collection> {
        val sourceFactory = UserCollectionDataSourceFactory(username, service)
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

    override fun getUserStatistics(username: String, resolution: String?, quantity: Int) {
        return service.getUserStatistics(username, resolution, quantity)
    }

    override fun followUser(username: String) {
        return service.followUser(username)
    }

    override fun unfollowUser(username: String) {
        return service.unfollowUser(username)
    }
}