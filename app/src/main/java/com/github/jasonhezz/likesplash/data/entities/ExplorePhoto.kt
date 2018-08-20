package com.github.jasonhezz.likesplash.data.entities

import com.squareup.moshi.Json

data class ExplorePhoto(
    @Json(name = "name") val name: String? = null,
    @Json(name = "descriptionFragment") val descriptionFragment: String? = null,
    @Json(name = "related") val related: List<Tag>? = null
)