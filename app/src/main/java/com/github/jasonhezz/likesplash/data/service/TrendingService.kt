package com.github.jasonhezz.likesplash.data.service

import com.github.jasonhezz.likesplash.data.entities.TrendingResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingService {
    @GET("feeds/home")
    fun getTrendingFeed(@Query("after") after: String? = null): Single<TrendingResponse>

    @GET("feeds/home")
    fun getTrendingFeed(
            @Query("after") after: String? = null,
            @Query("per_page") perPage: Int
    ): Call<TrendingResponse>

    @GET("feeds/following")
    fun getFollowingFeed(@Query("after") after: String? = null)
}