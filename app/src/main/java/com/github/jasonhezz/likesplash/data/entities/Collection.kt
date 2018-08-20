package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Collection(
    @Json(name = "id") val id: Int?,
    @Json(name = "featured") val featured: Boolean? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "published_at") val publishedAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "curated") val curated: Boolean = false,
    @Json(name = "total_photos") val totalPhotos: Int? = null,
    @Json(name = "private") val private: Boolean = false,
    @Json(name = "share_key") val shareKey: String? = null,
    @Json(name = "cover_photo") val coverPhoto: Photo? = null,
    @Json(name = "preview_photos") val previewPhotos: List<PreviewPhoto>? = null,
    @Json(name = "user") val user: User? = null,
    @Json(name = "links") val links: CollectionLinks? = null,
    @Json(name = "tags") val tags: List<Tag>? = null
) : Parcelable

