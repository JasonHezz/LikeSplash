package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserLinks(
    @SerializedName("self") val self: String?,
    @SerializedName("html") val html: String?,
    @SerializedName("photos") val photos: String?,
    @SerializedName("likes") val likes: String?,
    @SerializedName("portfolio") val portfolio: String?,
    @SerializedName("following") val following: String?,
    @SerializedName("followers") val followers: String?
) : Parcelable