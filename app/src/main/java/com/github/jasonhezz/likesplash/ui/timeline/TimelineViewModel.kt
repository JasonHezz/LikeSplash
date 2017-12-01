package com.github.jasonhezz.likesplash.ui.timeline

import android.arch.lifecycle.MutableLiveData
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.repository.RepostioryFactory
import com.github.jasonhezz.likesplash.ui.RxAwareViewModel
import com.github.jasonhezz.likesplash.util.extension.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/11/27.
 */
class TimelineViewModel : RxAwareViewModel() {

  var refreshOnStartup: Boolean = true
  private val nextPage = MutableLiveData<Int>().apply { postValue(1) }
  val messages = MutableLiveData<Resource>()
  val photos = MutableLiveData<List<Photo>>().apply { postValue(emptyList()) }

  init {
    // Eagerly refresh the initial page of trending
    if (refreshOnStartup) {
      fullRefresh()
    }
  }

  fun onListScrolledToEnd() {
    nextPage.value?.let {
      disposables += RepostioryFactory.makePhotoRepository().getListPhotos(it)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe { messages.value = Resource(Status.LOADING_MORE) }
          .subscribe(this::onSuccess, this::onError)
    }
  }

  fun fullRefresh() {
    disposables += RepostioryFactory.makePhotoRepository().getListPhotos()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { messages.value = Resource(Status.REFRESHING) }
        .subscribe(this::onRestSuccess, this::onError)
  }

  private fun onError(t: Throwable) {
    Timber.e(t)
    messages.value = Resource(Status.ERROR, t.message)
  }

  private fun onSuccess(response: List<Photo>) {
    messages.value = Resource(Status.SUCCESS)
    nextPage.postValue((nextPage.value ?: 1) + 1)
    val temp = (photos.value ?: mutableListOf()).toMutableList()
    temp.addAll(response)
    photos.postValue(temp)
  }

  private fun onRestSuccess(response: List<Photo>) {
    messages.value = Resource(Status.SUCCESS)
    nextPage.postValue(2)
    photos.value = response
  }
}