package com.github.jasonhezz.likesplash.inject

import com.github.jasonhezz.likesplash.repository.CollectionRepository
import com.github.jasonhezz.likesplash.repository.CollectionRepositoryIml
import com.github.jasonhezz.likesplash.repository.MockRepository
import com.github.jasonhezz.likesplash.repository.MockRepositoryIml
import com.github.jasonhezz.likesplash.repository.PhotoRepository
import com.github.jasonhezz.likesplash.repository.PhotoRepositoryIml
import com.github.jasonhezz.likesplash.repository.SearchRepository
import com.github.jasonhezz.likesplash.repository.SearchRepositoryIml
import com.github.jasonhezz.likesplash.repository.TrendingRepository
import com.github.jasonhezz.likesplash.repository.TrendingRepositoryIml
import com.github.jasonhezz.likesplash.repository.UserRepository
import com.github.jasonhezz.likesplash.repository.UserRepositoryIml
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