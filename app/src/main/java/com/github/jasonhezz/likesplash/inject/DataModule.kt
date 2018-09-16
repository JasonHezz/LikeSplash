package com.github.jasonhezz.likesplash.inject

import com.github.jasonhezz.likesplash.repository.*
import org.koin.dsl.module.module

val dataModule = module {
    single<TrendingRepository> {
        TrendingRepositoryIml(get())
    }
    single<PhotoRepository> {
        PhotoRepositoryIml(get())
    }
    single<UserRepository> {
        UserRepositoryIml(get())
    }
    single<SearchRepository> {
        SearchRepositoryIml(get())
    }
    single<CollectionRepository> {
        CollectionRepositoryIml(get())
    }
    single<MockRepository> {
        MockRepositoryIml(get())
    }
}