package com.github.jasonhezz.likesplash.util.network

import com.github.jasonhezz.likesplash.App
import com.github.jasonhezz.likesplash.data.api.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by JasonHezz on 2017/8/24.
 */

object NetModule {
  private val DEFAULT_CONNECT_TIMEOUT_MILLIS = 15L
  private val DEFAULT_READ_TIMEOUT_MILLIS = 20L
  private val DEFAULT_WRITE_TIMEOUT_MILLIS = 20L

  fun provideGson(): Gson = GsonBuilder().setLenient().create()


  fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
      .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.SECONDS)
      .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.SECONDS)
      .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.SECONDS)
//      .addInterceptor(provideAuthInterceptor())
      .addInterceptor(UserAgentInterceptor())
      .addInterceptor(FakeInterceptor(App.applicationContext()))
      .followRedirects(false)
      .build()


  fun provideRetrofit(): Retrofit = Retrofit.Builder()
      .baseUrl(UNSPLASH_NEW_BASE_URL)
      .client(provideOkHttpClient())
      .addConverterFactory(GsonConverterFactory.create(provideGson()))
      .build()

  fun provideAuthInterceptor(): Interceptor = AuthInterceptor()

  fun provideTrendingService(): TrendingService = provideRetrofit().create(
      TrendingService::class.java)

  fun providePhotoService(): PhotoService = provideRetrofit().create(PhotoService::class.java)

  fun provideUserService(): UserService = provideRetrofit().create(UserService::class.java)

  fun provideSearchService(): SearchService = provideRetrofit().create(SearchService::class.java)

  fun provideCollectionService(): CollectionService = provideRetrofit().create(
      CollectionService::class.java)

  fun provideExploreService(): ExploreService = provideRetrofit().create(
      ExploreService::class.java)
}