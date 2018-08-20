package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    @Json(name = "country") val country: String? = null,
    @Json(name = "city") val city: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "position") val position: Position? = null,
    @Json(name = "title") val title: String? = null
) : Parcelable