package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Me(
    @SerializedName("id") val id: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("first_name") val firstName: String? = null,
    @SerializedName("last_name") val lastName: String? = null,
    @SerializedName("portfolio_url") val portfolioUrl: String? = null,
    @SerializedName("bio") val bio: String? = null,
    @SerializedName("location") val location: String?,
    @SerializedName("totalLikes") val totalLikes: Int = 0,
    @SerializedName("totalPhotos") val totalPhotos: Int = 0,
    @SerializedName("total_collections") val totalCollections: Int = 0,
    @SerializedName("followed_by_user") val followedByUser: Boolean = false,
    @SerializedName("downloads") val downloads: Int = 0,
    @SerializedName("uploads_remaining") val uploadsRemaining: Int = 0,
    @SerializedName("instagram_username") val instagramUsername: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("links") val links: UserLinks? = null
) : Parcelable