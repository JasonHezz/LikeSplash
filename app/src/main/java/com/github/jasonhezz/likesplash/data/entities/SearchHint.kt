package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class SearchHint (
    @Json(name = "query") val query: String? = null ,
    @Json(name = "priority") val priority: Int?
    ) : Parcelable