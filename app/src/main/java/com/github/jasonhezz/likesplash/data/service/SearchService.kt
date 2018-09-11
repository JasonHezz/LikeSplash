package com.github.jasonhezz.likesplash.data.service

import com.github.jasonhezz.likesplash.data.entities.*
import com.github.jasonhezz.likesplash.data.entities.Collection
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {
    @GET("search/photos")
    fun searchPhotos(
            @Query("query") query: String,
            @Query("page") page: Int,
            @Query("per_page") perPage: Int
    ): Call<SearchPhotoResponse>

    @GET("search/collections")
    fun searchCollections(
            @Query("query") query: String,
            @Query("page") page: Int,
            @Query("per_page") perPage: Int
    ): Call<SearchCollectionResponse>

    @GET("search/users")
    fun searchUsers(
            @Query("query") query: String,
            @Query("page") page: Int,
            @Query("per_page") perPage: Int
    ): Call<SearchUserResponse>

    @GET("https://unsplash.com/nautocomplete/{keywords}?")
    fun autoComplete(@Path("keywords") keywords: String): Call<SearchHints>
}