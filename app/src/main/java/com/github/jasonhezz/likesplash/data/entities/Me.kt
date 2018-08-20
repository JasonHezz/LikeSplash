package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Me(
    @Json(name = "id") val id: String? = null,
    @Json(name = "username") val username: String? = null,
    @Json(name = "first_name") val firstName: String? = null,
    @Json(name = "last_name") val lastName: String? = null,
    @Json(name = "portfolio_url") val portfolioUrl: String? = null,
    @Json(name = "bio") val bio: String? = null,
    @Json(name = "location") val location: String?,
    @Json(name = "totalLikes") val totalLikes: Int = 0,
    @Json(name = "totalPhotos") val totalPhotos: Int = 0,
    @Json(name = "total_collections") val totalCollections: Int = 0,
    @Json(name = "followed_by_user") val followedByUser: Boolean = false,
    @Json(name = "downloads") val downloads: Int = 0,
    @Json(name = "uploads_remaining") val uploadsRemaining: Int = 0,
    @Json(name = "instagram_username") val instagramUsername: String? = null,
    @Json(name = "email") val email: String? = null,
    @Json(name = "links") val links: UserLinks? = null
) : Parcelable