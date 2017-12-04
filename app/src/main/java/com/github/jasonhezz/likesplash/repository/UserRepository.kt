package com.github.jasonhezz.likesplash.repository

import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.DAYS
import com.github.jasonhezz.likesplash.data.api.LATEST
import com.github.jasonhezz.likesplash.data.api.UserService
import io.reactivex.Single

/**
 * Created by JavaCoder on 2017/12/1.
 */
interface UserRepository {

  fun getUserProfile(username: String, w: Int? = null, h: Int? = null): Single<User>

  fun getUserFollowing(username: String,
      page: Int,
      per_page: Int): Single<List<User>>

  fun getUserFollowers(username: String,
      page: Int,
      per_page: Int): Single<List<User>>

  fun getUserLikes(username: String,
      page: Int = 1,
      per_page: Int = 10,
      orderBy: String = LATEST): Single<List<Photo>>

  fun getUserPhotos(username: String,
      page: Int = 1,
      per_page: Int = 10,
      orderBy: String = LATEST): Single<List<Photo>>

  fun getUserCollection(username: String,
      page: Int = 1,
      per_page: Int = 10,
      orderBy: String = LATEST): Single<List<Collection>>

  fun getUserStatistics(username: String, resolution: String = DAYS,
      quantity: Int = 30)

  fun followUser(username: String)

  fun unfollowUser(username: String)
}

class UserRepositoryIml(val service: UserService) : UserRepository {
  override fun getUserProfile(username: String, w: Int?, h: Int?): Single<User> {
    return service.getUserProfile(username, w, h)
  }

  override fun getUserFollowing(username: String, page: Int, per_page: Int): Single<List<User>> {
    return service.getUserFollowing(username, page, per_page)
  }

  override fun getUserFollowers(username: String, page: Int, per_page: Int): Single<List<User>> {
    return service.getUserFollowers(username, page, per_page)
  }

  override fun getUserLikes(username: String, page: Int, per_page: Int,
      orderBy: String): Single<List<Photo>> {
    return service.getUserLikes(username, page, per_page, orderBy)
  }

  override fun getUserPhotos(username: String, page: Int, per_page: Int,
      orderBy: String): Single<List<Photo>> {
    return service.getUserPhotos(username, page, per_page, orderBy)
  }

  override fun getUserCollection(username: String, page: Int, per_page: Int,
      orderBy: String): Single<List<Collection>> {
    return service.getUserCollection(username, page, per_page, orderBy)
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