package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Urls(
    @Json(name = "raw") val raw: String?,
    @Json(name = "full") val full: String?,
    @Json(name = "regular") val regular: String?,
    @Json(name = "small") val small: String?,
    @Json(name = "thumb") val thumb: String?,
    @Json(name = "custom") val custom: String?
) : Parcelable