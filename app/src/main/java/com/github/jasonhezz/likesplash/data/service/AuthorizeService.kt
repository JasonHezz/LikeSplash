package com.github.jasonhezz.likesplash.data.service

import com.github.jasonhezz.likesplash.data.entities.AccessToken
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthorizeService {
    @POST("oauth/token")
    fun getAccessToken(
        @Query("client_id") client_id: String,
        @Query("client_secret") client_secret: String,
        @Query("redirect_uri") redirect_uri: String,
        @Query("code") code: String,
        @Query("grant_type") grant_type: String
    ): Single<AccessToken>
}