package com.github.jasonhezz.likesplash.inject

import com.github.jasonhezz.likesplash.data.api.SplashConstants
import com.github.jasonhezz.likesplash.data.service.CollectionService
import com.github.jasonhezz.likesplash.data.service.MockService
import com.github.jasonhezz.likesplash.data.service.PhotoService
import com.github.jasonhezz.likesplash.data.service.SearchService
import com.github.jasonhezz.likesplash.data.service.TrendingService
import com.github.jasonhezz.likesplash.data.service.UserService
import com.github.jasonhezz.likesplash.util.network.FakeInterceptor
import com.github.jasonhezz.likesplash.util.network.UserAgentInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val DEFAULT_CONNECT_TIMEOUT_MILLIS = 15L
private const val DEFAULT_READ_TIMEOUT_MILLIS = 20L
private const val DEFAULT_WRITE_TIMEOUT_MILLIS = 20L

val netModule = module {
    single {
        FakeInterceptor(androidContext())
    }
    single {
        UserAgentInterceptor(androidContext())
    }
    single {
        OkHttpClient().newBuilder()
            .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.SECONDS)
            .addInterceptor(get<UserAgentInterceptor>())
            .addInterceptor(get<FakeInterceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(SplashConstants.UNSPLASH_NEW_BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder().build()).asLenient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(
            TrendingService::class.java
        )
    }
    single {
        get<Retrofit>().create(
            PhotoService::class.java
        )
    }
    single {
        get<Retrofit>().create(
            CollectionService::class.java
        )
    }
    single {
        get<Retrofit>().create(
            UserService::class.java
        )
    }
    single {
        get<Retrofit>().create(
            SearchService::class.java
        )
    }
    single {
        get<Retrofit>().create(
            MockService::class.java
        )
    }
}