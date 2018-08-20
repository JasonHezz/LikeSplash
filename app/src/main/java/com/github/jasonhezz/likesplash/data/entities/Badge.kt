package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Badge(
    @Json(name = "title") val title: String?,
    @Json(name = "primary") val primary: Boolean?,
    @Json(name = "slug") val slug: String?,
    @Json(name = "link") val link: String?
) : Parcelable