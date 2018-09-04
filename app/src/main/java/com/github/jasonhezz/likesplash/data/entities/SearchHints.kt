package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class SearchHints (
        @Json(name = "fuzzy") val fuzzy: List<SearchHint>? = null,
        @Json(name = "autocomplete") val autocomplete: List<SearchHint>? = null,
        @Json(name = "did_you_mean") val did_you_mean: List<SearchHint>? = null
) : Parcelable
