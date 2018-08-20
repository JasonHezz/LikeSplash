package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("id") val id: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("first_name") val firstName: String? = null,
    @SerializedName("last_name") val lastName: String? = null,
    @SerializedName("twitter_username") val twitterUsername: String? = null,
    @SerializedName("portfolio_url") val portfolioUrl: String? = null,
    @SerializedName("bio") val bio: String? = null,
    @SerializedName("location") val location: Location? = null,
    @SerializedName("links") val links: UserLinks? = null,
    @SerializedName("profile_image") val profile_image: ProfileImage? = null,
    @SerializedName("instagram_username") val instagramUsername: String?,
    @SerializedName("total_likes") val totalLikes: Int? = 0,
    @SerializedName("total_photos") val totalPhotos: Int? = 0,
    @SerializedName("total_collections") val totalCollections: Int? = 0,
    @SerializedName("followed_by_user") val followedByUser: Boolean? = false,
    @SerializedName("photos") val photos: List<Photo?>?,
    @SerializedName("badge") val badge: Badge?,
    @SerializedName("downloads") val downloads: Int?,
    @SerializedName("tags") val tags: Tags?,
    @SerializedName("followers_count") val followersCount: Int?,
    @SerializedName("following_count") val followingCount: Int?,
    @SerializedName("allow_messages") val allowMessages: Boolean?,
    @SerializedName("numeric_id") val numericId: Int?
) : Parcelable
