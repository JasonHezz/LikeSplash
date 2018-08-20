package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class RelatedCollections(
    @Json(name = "total") val total: Int? = null,
    @Json(name = "type") val type: String? = null,
    @Json(name = "results") val results: List<Collection>? = null
) : Parcelable