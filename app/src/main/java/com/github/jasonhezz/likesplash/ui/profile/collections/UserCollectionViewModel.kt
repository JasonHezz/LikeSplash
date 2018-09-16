package com.github.jasonhezz.likesplash.ui.profile.collections

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.repository.UserRepository

/**
 * Created by JavaCoder on 2017/11/27.
 */
class UserCollectionViewModel(
        userName: String,
        repository: UserRepository
) : ViewModel() {

    private val result = MutableLiveData<Listing<Collection>>()
    val collections = Transformations.switchMap(result, { it.pagedList })!!
    val networkState = Transformations.switchMap(result, { it.networkState })!!
    val refreshState = Transformations.switchMap(result, { it.refreshState })!!

    init {
        result.postValue(repository.getUserCollection(userName))
    }

    fun refresh() {
        result.value?.refresh?.invoke()
    }

    fun retry() {
        val listing = result.value
        listing?.retry?.invoke()
    }
}