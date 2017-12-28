package com.github.jasonhezz.likesplash.repository

import com.github.jasonhezz.likesplash.util.network.NetModule
import java.util.concurrent.Executors

/**
 * Created by JavaCoder on 2017/11/28.
 */
object RepostioryFactory {

  private val DISK_IO = Executors.newSingleThreadExecutor()
  private val NETWORK_IO = Executors.newFixedThreadPool(5)

  fun makeTrendingRepository(): TrendingRepository {
    return TrendingRepositoryIml(NetModule.provideTrendingService(),NETWORK_IO)
  }

  fun makePhotoRepository(): PhotoRepository {
    return PhotoRepositoryIml(NetModule.providePhotoService())
  }

  fun makeUserRepository(): UserRepository {
    return UserRepositoryIml(NetModule.provideUserService())
  }

  fun makeSearchRepository(): SearchRepository {
    return SearchRepositoryIml(NetModule.provideSearchService())
  }

  fun makeCollectionRepository(): CollectionRepository {
    return CollectionRepositoryIml(NetModule.provideCollectionService(), NETWORK_IO)
  }
}
