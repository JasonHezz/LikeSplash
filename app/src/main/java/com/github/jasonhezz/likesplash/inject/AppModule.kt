package com.github.jasonhezz.likesplash.inject

import com.github.jasonhezz.likesplash.ui.collection.CollectionDetailViewModel
import com.github.jasonhezz.likesplash.ui.collection.CuratedCollectionViewModel
import com.github.jasonhezz.likesplash.ui.collection.FeaturedCollectionViewModel
import com.github.jasonhezz.likesplash.ui.editorial.EditorialViewModel
import com.github.jasonhezz.likesplash.ui.explore.PopularCollectionViewModel
import com.github.jasonhezz.likesplash.ui.explore.PopularPhotoViewModel
import com.github.jasonhezz.likesplash.ui.follower.FollowerViewModel
import com.github.jasonhezz.likesplash.ui.following.FollowingViewModel
import com.github.jasonhezz.likesplash.ui.photodetail.PhotoDetailViewModel
import com.github.jasonhezz.likesplash.ui.profile.ProfileViewModel
import com.github.jasonhezz.likesplash.ui.profile.collections.UserCollectionViewModel
import com.github.jasonhezz.likesplash.ui.profile.likes.UserLikeViewModel
import com.github.jasonhezz.likesplash.ui.profile.photos.UserPhotoViewModel
import com.github.jasonhezz.likesplash.ui.trending.TrendingViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel { TrendingViewModel(get()) }
    viewModel { EditorialViewModel(get()) }
    viewModel { CuratedCollectionViewModel(get()) }
    viewModel { FeaturedCollectionViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { PopularPhotoViewModel(get()) }
    viewModel { (userName: String) -> FollowerViewModel(userName, get()) }
    viewModel { (userName: String) -> FollowingViewModel(userName, get()) }
    viewModel { (collectionId: String, isCurated: Boolean) -> CollectionDetailViewModel(collectionId, isCurated, get()) }
    viewModel { (userName: String) -> UserLikeViewModel(userName, get()) }
    viewModel { (userName: String) -> UserCollectionViewModel(userName, get()) }
    viewModel { (userName: String) -> UserPhotoViewModel(userName, get()) }
    viewModel { PopularCollectionViewModel(get()) }
    viewModel { (photoId: String) -> PhotoDetailViewModel(photoId, get()) }
}