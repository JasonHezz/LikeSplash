package com.github.jasonhezz.likesplash.ui.profile

import android.arch.lifecycle.MutableLiveData
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.repository.RepositoryFactory
import com.github.jasonhezz.likesplash.ui.RxAwareViewModel
import com.github.jasonhezz.likesplash.util.extension.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/12/1.
 */
class ProfileViewModel : RxAwareViewModel() {
    val messages = MutableLiveData<Resource>()
    val liveUser = MutableLiveData<User>()

    fun loadUser(user: User?) {
        liveUser.value = user
        disposables += RepositoryFactory.makeUserRepository().getUserProfile(user?.username ?: "")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { messages.value = Resource(Status.REFRESHING) }
            .subscribe(this::onSuccess, this::onError)
    }

    private fun onError(t: Throwable) {
        Timber.e(t)
        messages.value = Resource(Status.ERROR, t.message)
    }

    private fun onSuccess(user: User) {
        liveUser.value = user
        messages.value = Resource(Status.SUCCESS)
    }
}