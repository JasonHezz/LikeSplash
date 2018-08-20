package com.github.jasonhezz.likesplash.data.entities

import com.squareup.moshi.Json

data class TrendingResponse(
    @Json(name = "next_page") val nextPage: String?,
    @Json(name = "photos") val photos: List<Photo>?
)