package com.github.jasonhezz.likesplash.ui.explore

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.repository.Listing
import com.github.jasonhezz.likesplash.repository.SearchRepository

/**
 * Created by JavaCoder on 2017/11/27.
 */
class ExploreViewModel(private val repository: SearchRepository) : ViewModel() {

  //  private val nextPage = MutableLiveData<Int>().apply { value = 1 }
//  val photos = MutableLiveData<List<Photo>>().apply { value = emptyList() }
//  val defaultQuery = MutableLiveData<String>().apply { value = "Business" }
  private val result = MutableLiveData<Listing<Photo>>()
  private val queryLiveData = MutableLiveData<String>()
  val photos = Transformations.switchMap(result, { it.pagedList })!!
  val networkState = Transformations.switchMap(result, { it.networkState })!!
  val refreshState = Transformations.switchMap(result, { it.refreshState })!!

  var query: String? = null
    get() = queryLiveData.value
    set(value) {
      field = value
      queryLiveData.postValue(value)
      result.postValue(repository.searchPhotos(value ?: ""))
    }

  fun refresh() {
    result.value?.refresh?.invoke()
  }

  fun retry() {
    val listing = result.value
    listing?.retry?.invoke()
  }
}