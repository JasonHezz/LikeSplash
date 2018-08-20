package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exif(
    @Json(name = "aperture") val aperture: String?,
    @Json(name = "focal_length") val focalLength: String?,
    @Json(name = "iso") val iso: Int?,
    @Json(name = "model") val model: String?,
    @Json(name = "make") val make: String?,
    @Json(name = "exposure_time") val exposureTime: String?
) : Parcelable
