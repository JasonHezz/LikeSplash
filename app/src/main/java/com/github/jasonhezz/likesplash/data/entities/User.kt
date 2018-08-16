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
    @SerializedName("followed_by") val followedByUser: Boolean? = false,
    @SerializedName("location") val location: String? = null,
    @SerializedName("total_likes") val totalLikes: Int? = 0,
    @SerializedName("total_photos") val totalPhotos: Int? = 0,
    @SerializedName("total_collections") val totalCollections: Int? = 0,
    @SerializedName("profile_image") val profile_image: ProfileImage? = null,
    @SerializedName("links") val links: UserLinks? = null
) : Parcelable