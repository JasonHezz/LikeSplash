package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ProfileImage(
        @Json(name = "small") val small: String?,
        @Json(name = "medium") val medium: String?,
        @Json(name = "large") val large: String?
) : Parcelable