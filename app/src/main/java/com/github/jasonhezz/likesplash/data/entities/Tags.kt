package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tags(
    @Json(name = "custom") val customTags: List<Tag>?,
    @Json(name = "aggregated") val aggregatedTags: List<Tag>?
) : Parcelable