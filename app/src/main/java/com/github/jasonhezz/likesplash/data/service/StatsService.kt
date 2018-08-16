package com.github.jasonhezz.likesplash.data.service

import com.github.jasonhezz.likesplash.data.entities.MonthStats
import com.github.jasonhezz.likesplash.data.entities.TotalStats
import io.reactivex.Single
import retrofit2.http.GET

interface StatsService {

    @GET("stats/total")
    fun getTotal(): Single<TotalStats>

    @GET("/stats/month")
    fun getMonth(): Single<MonthStats>
}
