package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.LiveData
import com.github.jasonhezz.likesplash.data.entities.ExploreCollection
import com.github.jasonhezz.likesplash.data.entities.Wallpaper

/**
 * Created by JavaCoder on 2018/1/11.
 */
interface MockRepository {
    fun getListExploreCollection(): LiveData<List<ExploreCollection>>

    fun getListWallpapers(): LiveData<List<Wallpaper>>
}