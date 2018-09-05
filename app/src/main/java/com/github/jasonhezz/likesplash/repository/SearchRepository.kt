package com.github.jasonhezz.likesplash.repository

import android.arch.lifecycle.LiveData
import com.github.jasonhezz.likesplash.data.entities.*
import com.github.jasonhezz.likesplash.data.entities.Collection
import io.reactivex.Single

/**
 * Created by JavaCoder on 2017/12/6.
 */
interface SearchRepository {

    fun searchPagePhotos(query: String, page: Int = 1, perPage: Int = 24): Listing<Photo>

    fun searchPhotos(query: String, page: Int = 1, perPage: Int = 24): LiveData<List<Photo>>

    fun searchPageCollections(query: String, page: Int = 1, perPage: Int = 24): Single<List<Collection>>

    fun searchUsers(query: String, page: Int = 1, perPage: Int = 20): Single<List<User>>

    fun autoComplete(query: String): LiveData<SearchHints>
}