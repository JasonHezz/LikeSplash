package com.github.jasonhezz.likesplash.ui.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.repository.SearchRepository

/**
 * Created by JavaCoder on 2017/11/27.
 */
class PicturesViewModel(val repository: SearchRepository) : ViewModel() {
    //private val result = MutableLiveData<Listing<Photo>>()
    var querySubmit = MutableLiveData<String>()
    private val result = Transformations.switchMap(querySubmit) {
        repository.searchPagePhotos(it)
    }

    val photos = Transformations.switchMap(result) { it.pagedList }!!
    val networkState = Transformations.switchMap(result) { it.networkState }!!
    val refreshState = Transformations.switchMap(result) { it.refreshState }!!

    fun refresh() {
        result.value?.refresh?.invoke()
    }

    fun retry() {
        val listing = result.value
        listing?.retry?.invoke()
    }
}