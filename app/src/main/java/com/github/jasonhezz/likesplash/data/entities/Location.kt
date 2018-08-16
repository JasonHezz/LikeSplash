package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    @SerializedName("country") val country: String? = null,
    @SerializedName("city") val city: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("position") val position: Position? = null,
    @SerializedName("title") val title: String? = null
) : Parcelable