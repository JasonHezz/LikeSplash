package com.github.jasonhezz.likesplash.ui.profile.follower

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.repository.UserRepository

/**
 * Created by JavaCoder on 2017/11/27.
 */
class FollowerViewModel(
        private val userName: String,
        private val repository: UserRepository
) : ViewModel() {

    private val result = MutableLiveData<Listing<User>>()
    val followers = Transformations.switchMap(result) { it.pagedList }!!
    val networkState = Transformations.switchMap(result) { it.networkState }!!
    val refreshState = Transformations.switchMap(result) { it.refreshState }!!

    init {
        result.postValue(repository.getUserFollowers(userName))
    }

    fun refresh() {
        result.value?.refresh?.invoke()
    }

    fun retry() {
        val listing = result.value
        listing?.retry?.invoke()
    }
}