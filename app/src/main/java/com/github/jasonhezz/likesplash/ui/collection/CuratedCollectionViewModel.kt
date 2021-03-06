package com.github.jasonhezz.likesplash.ui.collection

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.repository.CollectionRepository
import com.github.jasonhezz.likesplash.repository.Listing

/**
 * Created by JavaCoder on 2017/12/12.
 */
class CuratedCollectionViewModel(private val repository: CollectionRepository) : ViewModel() {

  private val result = MutableLiveData<Listing<Collection>>()
  val collections = Transformations.switchMap(result, { it.pagedList })!!
  val networkState = Transformations.switchMap(result, { it.networkState })!!
  val refreshState = Transformations.switchMap(result, { it.refreshState })!!

  init {
    result.postValue(repository.getListCuratedCollections())
  }

  fun refresh() {
    result.value?.refresh?.invoke()
  }

  fun retry() {
    val listing = result.value
    listing?.retry?.invoke()
  }
}