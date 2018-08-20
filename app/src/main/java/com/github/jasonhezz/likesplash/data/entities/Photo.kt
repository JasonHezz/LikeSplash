package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * Created by JasonHezz on 2017/7/11.
 */
//@Parcelize not work under lolipop will throw install_failed_uid_changed
@Parcelize
data class Photo(
    @SerializedName("id") val id: String,
    @SerializedName("created_at")val createdAt: String? = null,
    @SerializedName("updated_at")val updatedAt: String? = null,
    @SerializedName("width")val width: Int,
    @SerializedName("height")val height: Int,
    @SerializedName("color")val color: String?,
    @SerializedName("downloads")val downloads: Int? = 0,
    @SerializedName("likes")val likes: Int? = 0,
    @SerializedName("views")val views: Int? = 0,
    @SerializedName("liked_by_user")val likedByUser: Boolean? = false,
    @SerializedName("description")val description: String? = null,
    @SerializedName("exif")val exif: Exif? = null,
    @SerializedName("location")val location: String? = null,
    @SerializedName("current_user_collections")val currentUserCollections: List<Collection>? = null,
    @SerializedName("urls")val urls: Urls? = null,
    @SerializedName("categories")val categories: List<Categories>? = null,
    @SerializedName("links")val links: PhotoLinks? = null,
    @SerializedName("story")val story: Story? = null,
    @SerializedName("tags")val tags: List<Tag>? = null,
    @SerializedName("relatedCollections")val relatedCollections: RelatedCollections? = null,
    @SerializedName("user")val user: User?
) : Serializable, Parcelable