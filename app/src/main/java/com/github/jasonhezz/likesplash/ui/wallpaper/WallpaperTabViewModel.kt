package com.github.jasonhezz.likesplash.ui.wallpaper

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.entities.Wallpaper
import com.github.jasonhezz.likesplash.repository.MockRepository

/**
 * Created by JavaCoder on 2018/1/4.
 */
class WallpaperTabViewModel(val repository: MockRepository) : ViewModel() {
    val result: LiveData<List<Wallpaper>> = repository.getListWallpapers()
}