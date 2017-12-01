package com.github.jasonhezz.likesplash.util.network

import com.github.jasonhezz.likesplash.data.api.PhotoService
import com.github.jasonhezz.likesplash.data.api.TrendingService
import com.github.jasonhezz.likesplash.data.api.UNSPLASH_BASE_URL
import com.github.jasonhezz.likesplash.data.api.UserService
import com.github.jasonhezz.likesplash.data.remote.AuthInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by JasonHezz on 2017/8/24.
 */

object NetModule {
  private val DEFAULT_CONNECT_TIMEOUT_MILLIS = 15L
  private val DEFAULT_READ_TIMEOUT_MILLIS = 20L
  private val DEFAULT_WRITE_TIMEOUT_MILLIS = 20L

  fun provideGson(): Gson = GsonBuilder().create()


  fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
      .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.SECONDS)
      .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.SECONDS)
      .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.SECONDS)
      .addInterceptor(provideAuthInterceptor())
      .build()


  fun provideRetrofit(): Retrofit = Retrofit.Builder()
      .baseUrl(UNSPLASH_BASE_URL)
      .client(provideOkHttpClient())
      .addConverterFactory(GsonConverterFactory.create(provideGson()))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()

  fun provideAuthInterceptor(): Interceptor = AuthInterceptor()

  fun provideTrendingService(): TrendingService = provideRetrofit().create(
      TrendingService::class.java)

  fun providePhotoService(): PhotoService = provideRetrofit().create(PhotoService::class.java)

  fun provideUserService(): UserService = provideRetrofit().create(UserService::class.java)
}