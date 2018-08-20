package com.github.jasonhezz.likesplash.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExplorePhoto(
    @Json(name = "name") val name: String? = null,
    @Json(name = "descriptionFragment") val descriptionFragment: String? = null,
    @Json(name = "related") val related: List<Tag>? = null
)