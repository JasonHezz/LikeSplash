package com.github.jasonhezz.likesplash.ui.trending

import android.arch.lifecycle.MutableLiveData
import android.net.Uri
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.TrendingFeed
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
class TrendingViewModel : RxAwareViewModel() {

  var refreshOnStartup: Boolean = true
  private val nextPage = MutableLiveData<String?>()
  val messages = MutableLiveData<Resource>()
  val photos = MutableLiveData<List<Photo>>().apply { postValue(emptyList()) }

  init {
    // Eagerly refresh the initial page of trending
    if (refreshOnStartup) {
      fullRefresh()
      refreshOnStartup = false
    }
  }

  fun onListScrolledToEnd() {
    nextPage.value?.let {
      disposables += RepostioryFactory.makeTrendingRepository().getTrendingFeed(nextPage.value)
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .doOnSubscribe { messages.value = Resource(Status.LOADING_MORE) }
          .subscribe(this::onSuccess, this::onError)
    }
  }

  fun fullRefresh() {
    disposables += RepostioryFactory.makeTrendingRepository().getTrendingFeed()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { messages.value = Resource(Status.REFRESHING) }
        .subscribe(this::onRestSuccess, this::onError)
  }

  private fun onError(t: Throwable) {
    Timber.e(t)
    messages.value = Resource(Status.ERROR, t.message)
  }

  private fun onSuccess(feed: TrendingFeed) {
    messages.value = Resource(Status.SUCCESS)
    val uri = Uri.parse(feed.next_page)
    val page = uri.getQueryParameter("after")
    nextPage.postValue(page)
    val temp = (photos.value ?: mutableListOf()).toMutableList()
    feed.photos?.let { temp.addAll(it) }
    photos.postValue(temp)
  }

  private fun onRestSuccess(feed: TrendingFeed) {
    messages.value = Resource(Status.SUCCESS)
    val uri = Uri.parse(feed.next_page)
    val page = uri.getQueryParameter("after")
    nextPage.postValue(page)
    feed.photos?.let { photos.value = it }
  }
}