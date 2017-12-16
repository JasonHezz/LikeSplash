package com.github.jasonhezz.likesplash.ui.explore

import android.arch.lifecycle.MutableLiveData
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.SearchPhotoResult
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
class ExploreViewModel : RxAwareViewModel() {

  private val nextPage = MutableLiveData<Int>().apply { value = 1 }
  val photos = MutableLiveData<List<Photo>>().apply { value = emptyList() }
  val query = MutableLiveData<String>().apply { value = "Business" }
  val messages = MutableLiveData<Resource>()

  init {
    fullRefresh()
  }

  fun onListScrolledToEnd() {
    if (nextPage.value != null) {
      disposables += RepostioryFactory.makeSearchRepository().searchPhotos(query.value!!,
          nextPage.value!!)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe { messages.value = Resource(Status.LOADING_MORE) }
          .subscribe(this::onSuccess, this::onError)
    }
  }

  fun fullRefresh() {
    disposables += RepostioryFactory.makeSearchRepository().searchPhotos(query.value!!)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { messages.value = Resource(Status.REFRESHING) }
        .subscribe(this::onRestSuccess, this::onError)
  }

  fun requery(requery: String) {
    query.value = requery
    fullRefresh()
  }

  private fun onError(t: Throwable) {
    Timber.e(t)
    messages.value = Resource(Status.ERROR, t.message)
  }

  private fun onSuccess(response: SearchPhotoResult) {
    messages.value = Resource(Status.SUCCESS)
    nextPage.postValue((nextPage.value ?: 1) + 1)
    val temp = (photos.value ?: mutableListOf()).toMutableList()
    response.results?.let {
      temp.addAll(it)
    }
    photos.postValue(temp)
  }

  private fun onRestSuccess(response: SearchPhotoResult) {
    messages.value = Resource(Status.SUCCESS)
    nextPage.postValue(2)
    photos.value = response.results
  }
}