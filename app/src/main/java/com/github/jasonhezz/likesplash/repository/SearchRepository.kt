package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.LiveData
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.User
import io.reactivex.Single

/**
 * Created by JavaCoder on 2017/12/6.
 */
interface SearchRepository {

    fun searchPagePhotos(
        query: String,
        page: Int = 1, per_page: Int = 20
    ): Listing<Photo>

    fun searchPhotos(
        query: String,
        page: Int = 1, per_page: Int = 20
    ): LiveData<List<Photo>>

    fun searchPageCollections(
        query: String,
        page: Int = 1, per_page: Int = 20
    ): Single<List<Collection>>

    fun searchUsers(query: String, page: Int = 1, per_page: Int = 20): Single<List<User>>
}