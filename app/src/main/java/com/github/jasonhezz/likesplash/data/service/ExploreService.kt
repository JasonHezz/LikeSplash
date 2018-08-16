package com.github.jasonhezz.likesplash.data.service

import com.github.jasonhezz.likesplash.data.entities.ExploreCollection
import com.github.jasonhezz.likesplash.util.network.FakeInterceptor
import retrofit2.Call
import retrofit2.http.GET

interface ExploreService {
    @GET("${FakeInterceptor.MOCK_API}/popular_collection")
    fun getExploreCollection(): Call<List<ExploreCollection>>
}