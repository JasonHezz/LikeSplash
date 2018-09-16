package com.github.jasonhezz.likesplash.data.service

import androidx.annotation.IntRange
import com.github.jasonhezz.likesplash.data.entities.DownLoadLink
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.enumerations.OrderBy
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface PhotoService {
    @GET("photos/")
    fun getListPhotos(
            @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 10,
            @Query("order_by") orderBy: OrderBy = OrderBy.LATEST
    ): Call<List<Photo>>

    @GET("photos/curated")
    fun getListCuratedPhotos(
            @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 10,
            @Query("order_by") orderBy: OrderBy
    ): Single<List<Photo>>

    @GET("photos/{id}/info?")
    fun getAPhoto(
            @Path("id") id: String,
            @Query("w") w: Int? = null,
            @Query("h") h: Int? = null
    ): Call<Photo>

    @GET("photos/random")
    fun getListRandomPhoto(
            @Query("collections") collections: String? = null,
            @Query("featured") featured: String? = null,
            @Query("username") username: String? = null,
            @Query("query") query: String? = null,
            @Query("orientation") orientation: String? = null,
            @Query("w") w: Int? = null,
            @Query("h") h: Int? = null,
            @IntRange(from = 1, to = 30) @Query("count") count: Int = 1
    ): Single<List<Photo>>

    @GET("photos/{id}/statistics")
    fun getAPhotoStatistics(
            @Path("id") id: Int,
            @Query("resolution") resolution: String? = null,
            @Query("quantity") quantity: Int = 30
    )

    @GET("photos/{id}/download")
    fun getAPhotoDownloadLink(
            @Path("id") id: String
    ): Single<DownLoadLink>

    //Update a photo on behalf of the logged-in user. This requires the write_photos scope.
    @PUT("photos/{id}")
    fun updateAPhoto(@Path("id") id: String)

    @POST("photos/{id}/like")
    fun likeAPhoto(@Path("id") id: String)

    //Note: This action is idempotent; sending the DELETE request to a single photo multiple times has no additional effect.
    @DELETE("photos/{id}/like")
    fun unlikeAPhoto(@Path("id") id: String)
}
