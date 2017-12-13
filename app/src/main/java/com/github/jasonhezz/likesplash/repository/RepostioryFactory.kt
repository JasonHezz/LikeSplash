package com.github.jasonhezz.likesplash.repository

import com.github.jasonhezz.likesplash.util.network.NetModule

/**
 * Created by JavaCoder on 2017/11/28.
 */
object RepostioryFactory {

  fun makeTrendingRepository(): TrendingRepository {
    return TrendingRepositoryIml(NetModule.provideTrendingService())
  }
}