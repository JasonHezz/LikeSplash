package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.jasonhezz.likesplash.data.ExploreCollection
import com.github.jasonhezz.likesplash.data.api.ExploreService
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

/**
 * Created by JavaCoder on 2018/1/11.
 */
interface ExploreRepository {
    fun getListExploreCollection(): LiveData<List<ExploreCollection>>
}

class ExploreRepositoryIml(val service: ExploreService) : ExploreRepository {
    val result = MutableLiveData<List<ExploreCollection>>()
    override fun getListExploreCollection(): LiveData<List<ExploreCollection>> {
        service.getExploreCollection().enqueue(
            object : retrofit2.Callback<List<ExploreCollection>> {
                override fun onFailure(call: Call<List<ExploreCollection>>?, t: Throwable?) {
                    Timber.d(t)
                }

                override fun onResponse(
                    call: Call<List<ExploreCollection>>?,
                    response: Response<List<ExploreCollection>>?
                ) {
                    response?.body()?.let {
                        result.postValue(it)
                    }
                }
            })
        return result
    }
}
