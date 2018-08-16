package com.github.jasonhezz.likesplash.data.entities

import com.google.gson.annotations.SerializedName

data class ExploreCollection(
    @SerializedName("name") val name: String? = null,
    @SerializedName("descriptionFragment") val descriptionFragment: String? = null,
    @SerializedName("collections") val collections: List<Collection>? = null
)