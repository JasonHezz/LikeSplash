package com.github.jasonhezz.likesplash.repository

import com.github.jasonhezz.likesplash.util.network.NetModule

/**
 * Created by JavaCoder on 2017/11/28.
 */
object RepostioryFactory {

  fun makeTrendingRepository(): TrendingRepository {
    return TrendingRepositoryIml(NetModule.provideTrendingService())
  }

  fun makePhotoRepository(): PhotoRepository {
    return PhotoRepositoryIml(NetModule.providePhotoService())
  }

  fun makeUserRepository(): UserRepository {
    return UserRepositoryIml(NetModule.provideUserService())
  }
}