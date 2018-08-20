package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tags(
    @SerializedName("custom") val customTags: List<Tag>?,
    @SerializedName("aggregated") val aggregatedTags: List<Tag>?
) : Parcelable