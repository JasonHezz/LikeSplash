package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Exif(
    @SerializedName("aperture") val aperture: String?,
    @SerializedName("focal_length") val focalLength: String?,
    @SerializedName("iso") val iso: Int?,
    @SerializedName("model") val model: String?,
    @SerializedName("make") val make: String?,
    @SerializedName("exposure_time") val exposureTime: String?
) : Parcelable
