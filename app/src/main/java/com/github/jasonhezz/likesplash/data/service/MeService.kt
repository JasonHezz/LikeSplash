package com.github.jasonhezz.likesplash.data.service

import com.github.jasonhezz.likesplash.data.entities.Me
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface MeService {
    //Note: To access a user’s private data, the user is required to authorize the read_user scope.
    // Without it, this request will return a 403 Forbidden response.
    @GET("me")
    fun getMeProfile(): Single<Me>

    @PUT("me")
    fun updateMeProfile(
        @Query("username") username: String? = null,
        @Query("first_name") first_name: String? = null,
        @Query("last_name") last_name: String? = null,
        @Query("email") email: String? = null,
        @Query("url") url: String? = null,
        @Query("location") location: String? = null,
        @Query("bio") bio: String? = null
    ): Single<Me>
}
