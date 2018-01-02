package com.github.jasonhezz.likesplash.ui.timeline

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.repository.Listing
import com.github.jasonhezz.likesplash.repository.PhotoRepository
import com.github.jasonhezz.likesplash.ui.RxAwareViewModel

/**
 * Created by JavaCoder on 2017/11/27.
 */
class TimelineViewModel(private val repository: PhotoRepository) : RxAwareViewModel() {

  /* var refreshOnStartup: Boolean = true
   private val nextPage = MutableLiveData<Int>().apply { postValue(1) }
   val messages = MutableLiveData<Resource>()
   val photos = MutableLiveData<List<Photo>>().apply { postValue(emptyList()) }*/
  private val result = MutableLiveData<Listing<Photo>>()
  val photos = Transformations.switchMap(result, { it.pagedList })!!
  val networkState = Transformations.switchMap(result, { it.networkState })!!
  val refreshState = Transformations.switchMap(result, { it.refreshState })!!

  init {
    // Eagerly refresh the initial page of trending
    result.postValue(repository.getListPhotos())
  }

  fun refresh() {
    result.value?.refresh?.invoke()
  }

  fun retry() {
    val listing = result.value
    listing?.retry?.invoke()
  }
}