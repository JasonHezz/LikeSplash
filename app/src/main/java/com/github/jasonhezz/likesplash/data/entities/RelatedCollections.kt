package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RelatedCollections(
    @SerializedName("total") val total: Int? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("results") val results: List<Collection>? = null
) : Parcelable