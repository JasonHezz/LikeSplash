package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Story(
    @Json(name = "description") val description: String? = null,
    @Json(name = "title") val title: String? = null
) : Parcelable