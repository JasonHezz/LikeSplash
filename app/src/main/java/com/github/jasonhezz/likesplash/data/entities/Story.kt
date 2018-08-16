package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Story(
    @SerializedName("description") val description: String? = null,
    @SerializedName("title") val title: String? = null
) : Parcelable