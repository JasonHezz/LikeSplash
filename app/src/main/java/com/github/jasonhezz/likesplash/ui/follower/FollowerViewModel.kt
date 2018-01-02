package com.github.jasonhezz.likesplash.ui.follower

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.repository.Listing
import com.github.jasonhezz.likesplash.repository.UserRepository

/**
 * Created by JavaCoder on 2017/11/27.
 */
class FollowerViewModel(private val userName: String,
    private val repository: UserRepository) : ViewModel() {

  private val result = MutableLiveData<Listing<User>>()
  val follwers = Transformations.switchMap(result, { it.pagedList })!!
  val networkState = Transformations.switchMap(result, { it.networkState })!!
  val refreshState = Transformations.switchMap(result, { it.refreshState })!!

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