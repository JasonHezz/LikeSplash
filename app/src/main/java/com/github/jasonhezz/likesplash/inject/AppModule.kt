package com.github.jasonhezz.likesplash.inject

import com.github.jasonhezz.likesplash.appInitializer.AppInitializers
import com.github.jasonhezz.likesplash.appInitializer.DayNightThemeInitializer
import com.github.jasonhezz.likesplash.appInitializer.FabricInitializer
import com.github.jasonhezz.likesplash.appInitializer.TimberInitializer
import com.github.jasonhezz.likesplash.ui.collection.curated.CuratedCollectionViewModel
import com.github.jasonhezz.likesplash.ui.collection.detail.CollectionDetailViewModel
import com.github.jasonhezz.likesplash.ui.collection.featured.FeaturedCollectionViewModel
import com.github.jasonhezz.likesplash.ui.explore.PopularCollectionViewModel
import com.github.jasonhezz.likesplash.ui.explore.PopularPhotoViewModel
import com.github.jasonhezz.likesplash.ui.home.editorial.EditorialViewModel
import com.github.jasonhezz.likesplash.ui.home.trending.TrendingViewModel
import com.github.jasonhezz.likesplash.ui.photodetail.PhotoDetailViewModel
import com.github.jasonhezz.likesplash.ui.profile.ProfileViewModel
import com.github.jasonhezz.likesplash.ui.profile.collections.UserCollectionViewModel
import com.github.jasonhezz.likesplash.ui.profile.follower.FollowerViewModel
import com.github.jasonhezz.likesplash.ui.profile.following.FollowingViewModel
import com.github.jasonhezz.likesplash.ui.profile.likes.UserLikeViewModel
import com.github.jasonhezz.likesplash.ui.profile.photos.UserPhotoViewModel
import com.github.jasonhezz.likesplash.ui.search.SearchViewModel
import com.github.jasonhezz.likesplash.ui.wallpaper.WallpaperTabViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { TimberInitializer() }
    single { DayNightThemeInitializer() }
    single { FabricInitializer() }
    single { AppInitializers(get<TimberInitializer>(), get<DayNightThemeInitializer>(), get<FabricInitializer>()) }
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
    viewModel { SearchViewModel(get()) }
    viewModel { WallpaperTabViewModel(get()) }
}