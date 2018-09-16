package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Position(
        @Json(name = "latitude") val latitude: Double? = null,
        @Json(name = "longitude") val longitude: Double? = null
) : Parcelable