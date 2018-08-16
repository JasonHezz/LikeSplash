package com.github.jasonhezz.likesplash.data.entities

import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    @SerializedName("next_page") val nextPage: String?,
    @SerializedName("photos") val photos: List<Photo>?
)