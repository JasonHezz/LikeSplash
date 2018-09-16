package com.github.jasonhezz.likesplash.data.service

import com.github.jasonhezz.likesplash.data.entities.AccessToken
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthorizeService {
    @POST("oauth/token")
    fun getAccessToken(
            @Query("client_id") clientId: String,
            @Query("client_secret") clientSecret: String,
            @Query("redirect_uri") redirectUri: String,
            @Query("code") code: String,
            @Query("grant_type") grantType: String
    ): Single<AccessToken>
}