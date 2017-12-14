package com.github.jasonhezz.likesplash.repository

import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.SearchPhotoResult
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.SearchService
import io.reactivex.Single

/**
 * Created by JavaCoder on 2017/12/6.
 */
interface SearchRepository {

  fun searchPhotos(query: String,
      page: Int = 1, per_page: Int = 10): Single<SearchPhotoResult>

  fun searchCollections(query: String,
      page: Int = 1, per_page: Int = 10): Single<List<Collection>>

  fun searchUsers(query: String, page: Int = 1, per_page: Int = 10): Single<List<User>>
}

class SearchRepositoryIml(val service: SearchService) : SearchRepository {

  override fun searchPhotos(query: String, page: Int, per_page: Int): Single<SearchPhotoResult> {
    return service.searchPhotos(query, page, per_page)
  }

  override fun searchCollections(query: String, page: Int,
      per_page: Int): Single<List<Collection>> {
    return service.searchCollections(query, page, per_page)
  }

  override fun searchUsers(query: String, page: Int, per_page: Int): Single<List<User>> {
    return service.searchUsers(query, page, per_page)
  }
}