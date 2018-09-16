package com.github.jasonhezz.likesplash.data.service

import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Photo
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface CollectionService {
    @GET("collections/")
    fun getListCollections(
            @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 10
    ): Call<List<Collection>>

    @GET("collections/curated")
    fun getListCuratedCollections(
            @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 10
    ): Call<List<Collection>>

    @GET("collections/featured")
    fun getListFeaturedCollections(
            @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 10
    ): Call<List<Collection>>

    @GET("collections/{id}")
    fun getACollection(
            @Path("id") id: String, @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 10
    ): Single<Collection>

    @GET("collections/curated/{id}")
    fun getACuratedCollection(
            @Path("id") id: String, @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 10
    ): Single<Collection>

    @GET("collections/{id}/photos")
    fun getCollectionPhotos(
            @Path("id") id: String, @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 10
    ): Call<List<Photo>>

    @GET("collections/curated/{id}/photos")
    fun getCuratedCollectionPhotos(
            @Path("id") id: String, @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 10
    ): Call<List<Photo>>

    @GET("collections/{id}/related")
    fun getRelatedCollections(@Path("id") id: String)

    @POST("collections")
    fun createANewCollection(
            @Query("title") title: String,
            @Query("description") description: String? = null,
            @Query("private") private: Boolean
    ): Single<Collection>

    @POST("collections/{id}")
    fun updateANewCollection(@Path("id") id: String): Single<Collection>

    @DELETE("collections/{id}")
    fun deleteCollection(@Path("id") id: String): Single<ResponseBody>

    @POST("collections/collection_id/add")
    fun addPhotoToCollection(
            @Path("collection_id") collectionId: Int,
            @Query("photo_id") photoId: String
    ): Single<ResponseBody>

    //Remove a photo from one of the logged-in user’s collections. Requires the write_collections scope
    @POST("collections/collection_id/remove")
    fun removePhotoToCollection(
            @Path("collection_id") collectionId: Int,
            @Query("photo_id") photoId: String
    ): Single<ResponseBody>
}
