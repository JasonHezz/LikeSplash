package com.github.jasonhezz.likesplash.ui.explore

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.repository.SearchRepository

/**
 * Created by JavaCoder on 2018/1/4.
 */
class PopularPhotoViewModel(private val repository: SearchRepository) : ViewModel() {
  var businessPhoto = MutableLiveData<List<Photo>>()
  var girlPhoto = MutableLiveData<List<Photo>>()
  var naturePhoto = MutableLiveData<List<Photo>>()
  var technologyPhoto = MutableLiveData<List<Photo>>()
  var foodPhoto = MutableLiveData<List<Photo>>()
  var travelPhoto = MutableLiveData<List<Photo>>()
  var happyPhoto = MutableLiveData<List<Photo>>()
  var coolPhoto = MutableLiveData<List<Photo>>()

  init {

  }
}