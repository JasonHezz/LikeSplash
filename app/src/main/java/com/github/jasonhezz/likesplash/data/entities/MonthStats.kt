package com.github.jasonhezz.likesplash.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MonthStats(
    @SerializedName("photos") val photos: Int?,
    @SerializedName("download") val download: Int?,
    @SerializedName("views") val views: Int?,
    @SerializedName("likes") val likes: Int?,
    @SerializedName("new_photos") val newPhotos: Int?,
    @SerializedName("new_photographers") val newPhotographers: Int?,
    @SerializedName("new_pixels") val newPixels: Int?,
    @SerializedName("new_developers") val newDevelopers: Int?,
    @SerializedName("new_applications") val newApplications: Int?,
    @SerializedName("new_requestS") val newRequests: Int?
) : Parcelable