package com.github.jasonhezz.likesplash.data.service

import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.SearchPhotoResponse
import com.github.jasonhezz.likesplash.data.entities.User
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search/photos")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<SearchPhotoResponse>

    @GET("search/photos")
    fun searchCollections(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Single<List<Collection>>

    @GET("search/users")
    fun searchUsers(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Single<List<User>>
}