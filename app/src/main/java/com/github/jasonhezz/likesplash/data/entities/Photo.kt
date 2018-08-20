package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Created by JasonHezz on 2017/7/11.
 */
//@Parcelize not work under lolipop will throw install_failed_uid_changed
@Parcelize
@JsonClass(generateAdapter = true)
data class Photo(
    @Json(name = "id") val id: String,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int,
    @Json(name = "color") val color: String?,
    @Json(name = "downloads") val downloads: Int? = 0,
    @Json(name = "likes") val likes: Int? = 0,
    @Json(name = "views") val views: Int? = 0,
    @Json(name = "liked_by_user") val likedByUser: Boolean? = false,
    @Json(name = "description") val description: String? = null,
    @Json(name = "exif") val exif: Exif? = null,
    @Json(name = "location") val location: String? = null,
    @Json(name = "current_user_collections") val currentUserCollections: List<Collection>? = null,
    @Json(name = "urls") val urls: Urls? = null,
    @Json(name = "categories") val categories: List<Categories>? = null,
    @Json(name = "links") val links: PhotoLinks? = null,
    @Json(name = "story") val story: Story? = null,
    @Json(name = "tags") val tags: List<Tag>? = null,
    @Json(name = "relatedCollections") val relatedCollections: RelatedCollections? = null,
    @Json(name = "user") val user: User?
) : Serializable, Parcelable