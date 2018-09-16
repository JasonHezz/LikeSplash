package com.github.jasonhezz.likesplash.ui.explore

import androidx.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.repository.SearchRepository

/**
 * Created by JavaCoder on 2018/1/4.
 */
class PopularPhotoViewModel(private val repository: SearchRepository) : ViewModel() {
    val businessPhoto = repository.searchPhotos("Business", 1, 3)
    var girlPhoto = repository.searchPhotos("Women", 1, 3)
    var naturePhoto = repository.searchPhotos("Nature", 1, 3)
    var technologyPhoto = repository.searchPhotos("Technology", 1, 3)
    var foodPhoto = repository.searchPhotos("Food", 1, 3)
    var travelPhoto = repository.searchPhotos("Travel", 1, 3)
    var happyPhoto = repository.searchPhotos("Happy", 1, 3)
    var coolPhoto = repository.searchPhotos("Cool", 1, 3)
}