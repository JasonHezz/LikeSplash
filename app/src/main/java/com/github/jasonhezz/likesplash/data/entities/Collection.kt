package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Collection(
    @SerializedName("id") val id: Int?,
    @SerializedName("featured") val featured: Boolean? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("published_at") val publishedAt: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null,
    @SerializedName("curated") val curated: Boolean = false,
    @SerializedName("total_photos") val totalPhotos: Int? = null,
    @SerializedName("private") val private: Boolean = false,
    @SerializedName("share_key") val shareKey: String? = null,
    @SerializedName("cover_photo") val coverPhoto: Photo? = null,
    @SerializedName("preview_photos") val previewPhotos: List<Photo>? = null,
    @SerializedName("user") val user: User? = null,
    @SerializedName("links") val links: CollectionLinks? = null,
    @SerializedName("tags") val tags: List<Tag>? = null
) : Parcelable

