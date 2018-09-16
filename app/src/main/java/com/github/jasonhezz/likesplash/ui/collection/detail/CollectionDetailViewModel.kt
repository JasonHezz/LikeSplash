package com.github.jasonhezz.likesplash.ui.collection.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.repository.CollectionRepository

/**
 * Created by JavaCoder on 2017/12/12.
 */
class CollectionDetailViewModel(
        val id: String,
        val isCurated: Boolean,
        repository: CollectionRepository
) : ViewModel() {

    private val result = MutableLiveData<Listing<Photo>>()
    val photos = Transformations.switchMap(result) { it.pagedList }!!
    val networkState = Transformations.switchMap(result) { it.networkState }!!
    val refreshState = Transformations.switchMap(result) { it.refreshState }!!

    init {
        result.postValue(if (isCurated) repository.getCuratedCollectionPhotos(id) else repository.getCollectionPhotos(id))
    }

    fun refresh() {
        result.value?.refresh?.invoke()
    }

    fun retry() {
        val listing = result.value
        listing?.retry?.invoke()
    }
}