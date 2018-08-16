package com.github.jasonhezz.likesplash.data.entities

import com.google.gson.annotations.SerializedName

data class TotalStats(
    @SerializedName("photos") val photos: Int?,
    @SerializedName("download") val download: Int?,
    @SerializedName("views") val views: Int?,
    @SerializedName("likes") val likes: Int?,
    @SerializedName("photographers") val photographers: Int?,
    @SerializedName("pixels") val pixels: Int?,
    @SerializedName("downloads_per_second") val downloadsPerSecond: Int,
    @SerializedName("views_per_second") val viewsPerSecond: Int,
    @SerializedName("developers") val developers: Int?,
    @SerializedName("applications") val applications: Int?,
    @SerializedName("requests") val requests: Int
)