package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MonthStats(
    @Json(name = "photos") val photos: Int?,
    @Json(name = "download") val download: Int?,
    @Json(name = "views") val views: Int?,
    @Json(name = "likes") val likes: Int?,
    @Json(name = "new_photos") val newPhotos: Int?,
    @Json(name = "new_photographers") val newPhotographers: Int?,
    @Json(name = "new_pixels") val newPixels: Int?,
    @Json(name = "new_developers") val newDevelopers: Int?,
    @Json(name = "new_applications") val newApplications: Int?,
    @Json(name = "new_requestS") val newRequests: Int?
) : Parcelable