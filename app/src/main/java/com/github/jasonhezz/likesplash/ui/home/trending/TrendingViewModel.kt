package com.github.jasonhezz.likesplash.ui.home.trending

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.repository.TrendingRepository

/**
 * Created by JavaCoder on 2017/11/27.
 */
class TrendingViewModel(val repository: TrendingRepository) : ViewModel() {
    private val result = MutableLiveData<Listing<Photo>>()
    val photos = Transformations.switchMap(result) { it.pagedList }!!
    val networkState = Transformations.switchMap(result) { it.networkState }!!
    val refreshState = Transformations.switchMap(result) { it.refreshState }!!

    init {
        result.postValue(repository.getTrendingFeed())
    }

    fun refresh() {
        result.value?.refresh?.invoke()
    }

    fun retry() {
        val listing = result.value
        listing?.retry?.invoke()
    }
}