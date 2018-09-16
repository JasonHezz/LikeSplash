package com.github.jasonhezz.likesplash.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.jasonhezz.likesplash.data.entities.ExploreCollection
import com.github.jasonhezz.likesplash.data.entities.Wallpaper
import com.github.jasonhezz.likesplash.data.service.MockService
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class MockRepositoryIml(val service: MockService) : MockRepository {

    override fun getListExploreCollection(): LiveData<List<ExploreCollection>> {
        val result = MutableLiveData<List<ExploreCollection>>()
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

    override fun getListWallpapers(): LiveData<List<Wallpaper>> {
        val result = MutableLiveData<List<Wallpaper>>()
        service.getWallpapers().enqueue(
                object : retrofit2.Callback<List<Wallpaper>> {
                    override fun onFailure(call: Call<List<Wallpaper>>?, t: Throwable?) {
                        Timber.d(t)
                    }

                    override fun onResponse(
                            call: Call<List<Wallpaper>>?,
                            response: Response<List<Wallpaper>>?
                    ) {
                        response?.body()?.let {
                            result.postValue(it)
                        }
                    }
                })
        return result
    }
}