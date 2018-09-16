package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class CollectionLinks(
        @Json(name = "self") val self: String?,
        @Json(name = "html") val html: String?,
        @Json(name = "photos") val photos: String?,
        @Json(name = "related") val related: String?
) : Parcelable