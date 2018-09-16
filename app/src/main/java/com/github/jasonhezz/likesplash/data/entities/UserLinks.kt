package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserLinks(
        @Json(name = "self") val self: String?,
        @Json(name = "html") val html: String?,
        @Json(name = "photos") val photos: String?,
        @Json(name = "likes") val likes: String?,
        @Json(name = "portfolio") val portfolio: String?,
        @Json(name = "following") val following: String?,
        @Json(name = "followers") val followers: String?
) : Parcelable