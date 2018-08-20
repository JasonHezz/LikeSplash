package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id") val id: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "username") val username: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "first_name") val firstName: String? = null,
    @Json(name = "last_name") val lastName: String? = null,
    @Json(name = "twitter_username") val twitterUsername: String? = null,
    @Json(name = "portfolio_url") val portfolioUrl: String? = null,
    @Json(name = "bio") val bio: String? = null,
    @Json(name = "location") val location: String? = null,
    @Json(name = "links") val links: UserLinks? = null,
    @Json(name = "profile_image") val profile_image: ProfileImage? = null,
    @Json(name = "instagram_username") val instagramUsername: String?,
    @Json(name = "total_likes") val totalLikes: Int? = 0,
    @Json(name = "total_photos") val totalPhotos: Int? = 0,
    @Json(name = "total_collections") val totalCollections: Int? = 0,
    @Json(name = "followed_by_user") val followedByUser: Boolean? = false,
    @Json(name = "photos") val photos: List<Photo?>?,
    @Json(name = "badge") val badge: Badge?,
    @Json(name = "downloads") val downloads: Int?,
    @Json(name = "tags") val tags: Tags?,
    @Json(name = "followers_count") val followersCount: Int?,
    @Json(name = "following_count") val followingCount: Int?,
    @Json(name = "allow_messages") val allowMessages: Boolean?,
    @Json(name = "numeric_id") val numericId: Int?
) : Parcelable
