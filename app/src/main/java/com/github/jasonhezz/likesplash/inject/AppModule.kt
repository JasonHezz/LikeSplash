package com.github.jasonhezz.likesplash.inject

import com.github.jasonhezz.likesplash.App
import com.github.jasonhezz.likesplash.data.api.CollectionService
import com.github.jasonhezz.likesplash.data.api.ExploreService
import com.github.jasonhezz.likesplash.data.api.PhotoService
import com.github.jasonhezz.likesplash.data.api.SearchService
import com.github.jasonhezz.likesplash.data.api.TrendingService
import com.github.jasonhezz.likesplash.data.api.UNSPLASH_NEW_BASE_URL
import com.github.jasonhezz.likesplash.data.api.UserService
import com.github.jasonhezz.likesplash.repository.CollectionRepository
import com.github.jasonhezz.likesplash.repository.CollectionRepositoryIml
import com.github.jasonhezz.likesplash.repository.ExploreRepository
import com.github.jasonhezz.likesplash.repository.ExploreRepositoryIml
import com.github.jasonhezz.likesplash.repository.PhotoRepository
import com.github.jasonhezz.likesplash.repository.PhotoRepositoryIml
import com.github.jasonhezz.likesplash.repository.SearchRepository
import com.github.jasonhezz.likesplash.repository.SearchRepositoryIml
import com.github.jasonhezz.likesplash.repository.TrendingRepository
import com.github.jasonhezz.likesplash.repository.TrendingRepositoryIml
import com.github.jasonhezz.likesplash.repository.UserRepository
import com.github.jasonhezz.likesplash.repository.UserRepositoryIml
import com.github.jasonhezz.likesplash.ui.collection.CollectionDetailViewModel
import com.github.jasonhezz.likesplash.ui.collection.CuratedCollectionViewModel
import com.github.jasonhezz.likesplash.ui.collection.FeaturedCollectionViewModel
import com.github.jasonhezz.likesplash.ui.explore.PopularCollectionViewModel
import com.github.jasonhezz.likesplash.ui.explore.PopularPhotoViewModel
import com.github.jasonhezz.likesplash.ui.follower.FollowerViewModel
import com.github.jasonhezz.likesplash.ui.following.FollowingViewModel
import com.github.jasonhezz.likesplash.ui.profile.ProfileViewModel
import com.github.jasonhezz.likesplash.ui.profile.collections.UserCollectionViewModel
import com.github.jasonhezz.likesplash.ui.profile.likes.UserLikeViewModel
import com.github.jasonhezz.likesplash.ui.profile.photos.UserPhotoViewModel
import com.github.jasonhezz.likesplash.ui.timeline.TimelineViewModel
import com.github.jasonhezz.likesplash.ui.trending.TrendingViewModel
import com.github.jasonhezz.likesplash.util.network.FakeInterceptor
import com.github.jasonhezz.likesplash.util.network.UserAgentInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val DEFAULT_CONNECT_TIMEOUT_MILLIS = 15L
const val DEFAULT_READ_TIMEOUT_MILLIS = 20L
const val DEFAULT_WRITE_TIMEOUT_MILLIS = 20L

val appModule = module {
    single {
        GsonBuilder().setLenient().create()
    }
    single {
        FakeInterceptor(App.applicationContext())
    }
    single {
        UserAgentInterceptor()
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
            .baseUrl(UNSPLASH_NEW_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
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
            ExploreService::class.java
        )
    }
    single {
        TrendingRepositoryIml(get()) as TrendingRepository
    }
    single {
        PhotoRepositoryIml(get()) as PhotoRepository
    }
    single {
        UserRepositoryIml(get()) as UserRepository
    }
    single {
        SearchRepositoryIml(get()) as SearchRepository
    }
    single {
        CollectionRepositoryIml(get()) as CollectionRepository
    }
    single {
        ExploreRepositoryIml(get()) as ExploreRepository
    }
    viewModel { TrendingViewModel(get()) }
    viewModel { TimelineViewModel(get()) }
    viewModel { CuratedCollectionViewModel(get()) }
    viewModel { FeaturedCollectionViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { PopularPhotoViewModel(get()) }
    viewModel { FollowerViewModel(getProperty("id"), get()) }
    viewModel { FollowingViewModel(getProperty("id"), get()) }
    viewModel { CollectionDetailViewModel(getProperty("id"), get()) }
    viewModel { UserLikeViewModel(getProperty("id"), get()) }
    viewModel { UserCollectionViewModel(getProperty("id"), get()) }
    viewModel { UserPhotoViewModel(getProperty("id"), get()) }
    viewModel { PopularCollectionViewModel(get()) }
}