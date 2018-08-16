package com.github.jasonhezz.likesplash.data.service

import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.data.enumerations.OrderBy
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("users/{username}")
    fun getUserProfile(
        @Path("username") username: String,
        @Query("w") w: Int?,
        @Query("h") h: Int?
    ): Single<User>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Single<List<User>>

    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Call<List<User>>

    @GET("users/{username}/likes")
    fun getUserLikes(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") orderBy: OrderBy = OrderBy.LATEST
    ): Call<List<Photo>>

    @GET("users/{username}/photos")
    fun getUserPhotos(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") orderBy: OrderBy = OrderBy.LATEST
    ): Call<List<Photo>>

    @GET("users/{username}/collections")
    fun getUserCollection(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("order_by") orderBy: OrderBy = OrderBy.LATEST
    ): Call<List<Collection>>

    @GET("users/{username}/statistics")
    fun getUserStatistics(
        @Path("username") username: String,
        @Query("resolution") resolution: String? = null,
        @Query("quantity") quantity: Int = 30
    )

    @POST("users/{username}/follow")
    fun followUser(@Path("username") username: String)

    @DELETE("users/{username}/follow")
    fun unfollowUser(@Path("username") username: String)
}