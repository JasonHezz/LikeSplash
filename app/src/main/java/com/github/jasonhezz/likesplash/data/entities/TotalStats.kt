package com.github.jasonhezz.likesplash.data.entities

import com.squareup.moshi.Json

data class TotalStats(
    @Json(name = "photos") val photos: Int?,
    @Json(name = "download") val download: Int?,
    @Json(name = "views") val views: Int?,
    @Json(name = "likes") val likes: Int?,
    @Json(name = "photographers") val photographers: Int?,
    @Json(name = "pixels") val pixels: Int?,
    @Json(name = "downloads_per_second") val downloadsPerSecond: Int,
    @Json(name = "views_per_second") val viewsPerSecond: Int,
    @Json(name = "developers") val developers: Int?,
    @Json(name = "applications") val applications: Int?,
    @Json(name = "requests") val requests: Int
)