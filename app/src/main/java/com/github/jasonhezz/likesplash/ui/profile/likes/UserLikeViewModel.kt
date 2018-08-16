package com.github.jasonhezz.likesplash.ui.profile.likes

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.repository.Listing
import com.github.jasonhezz.likesplash.repository.UserRepository

/**
 * Created by JavaCoder on 2017/11/27.
 */
class UserLikeViewModel(
    userName: String,
    repository: UserRepository
) : ViewModel() {
    private val result = MutableLiveData<Listing<Photo>>()
    val photos = Transformations.switchMap(result, { it.pagedList })!!
    val networkState = Transformations.switchMap(result, { it.networkState })!!
    val refreshState = Transformations.switchMap(result, { it.refreshState })!!

    init {
        result.postValue(repository.getUserLikes(userName))
    }

    fun refresh() {
        result.value?.refresh?.invoke()
    }

    fun retry() {
        val listing = result.value
        listing?.retry?.invoke()
    }
}