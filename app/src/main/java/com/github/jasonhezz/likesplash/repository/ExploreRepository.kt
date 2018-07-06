package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.LiveData
import com.github.jasonhezz.likesplash.data.ExploreCollection

/**
 * Created by JavaCoder on 2018/1/11.
 */
interface ExploreRepository {
    fun getListExploreCollection(): LiveData<List<ExploreCollection>>
}

/*class ExploreRepositoryIml() : ExploreRepository {
  override fun getListExploreCollection(): LiveData<List<ExploreCollection>> {

  }
}*/
