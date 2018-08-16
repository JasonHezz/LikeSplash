package com.github.jasonhezz.likesplash.repository

import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Listing
import com.github.jasonhezz.likesplash.data.entities.Photo

/**
 * Created by JavaCoder on 2017/12/13.
 */
interface CollectionRepository {

    fun getListCuratedCollections(perPage: Int = 20): Listing<Collection>

    fun getListFeaturedCollections(perPage: Int = 20): Listing<Collection>

    fun getCuratedCollectionPhotos(id: String, perPage: Int = 20): Listing<Photo>

    fun getCollectionPhotos(id: String, perPage: Int = 20): Listing<Photo>
}

