package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoLinks(
    @Json(name = "self") val self: String? = null,
    @Json(name = "html") val html: String? = null,
    @Json(name = "download") val download: String? = null,
    @Json(name = "download_location") val downloadLocation: String? = null
) : Parcelable