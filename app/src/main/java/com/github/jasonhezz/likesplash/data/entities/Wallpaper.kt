package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Wallpaper(
        @Json(name = "id") val id: Int,
        @Json(name = "wallpaperKey") val wallpaperKey: String,
        @Json(name = "title") val title: String,
        @Json(name = "description") val description: String,
        @Json(name = "cover_photo") val coverPhoto: Photo
) : Parcelable