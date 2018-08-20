package com.github.jasonhezz.likesplash.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExploreCollection(
    @Json(name = "name") val name: String? = null,
    @Json(name = "descriptionFragment") val descriptionFragment: String? = null,
    @Json(name = "collections") val collections: List<Collection>? = null
)