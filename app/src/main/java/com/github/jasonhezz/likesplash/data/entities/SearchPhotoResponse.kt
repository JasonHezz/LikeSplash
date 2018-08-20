package com.github.jasonhezz.likesplash.data.entities

import com.squareup.moshi.Json

data class SearchPhotoResponse(
    @Json(name = "total") val total: Int?,
    @Json(name = "total_pages") val totalPages: Int?,
    @Json(name = "results") val results: List<Photo>?
)

