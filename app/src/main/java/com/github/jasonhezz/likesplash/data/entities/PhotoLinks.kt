package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoLinks(
    @SerializedName("self") val self: String? = null,
    @SerializedName("html") val html: String? = null,
    @SerializedName("download") val download: String? = null,
    @SerializedName("download_location") val downloadLocation: String? = null
) : Parcelable