package com.github.jasonhezz.likesplash.data.entities

import com.google.gson.annotations.SerializedName

data class ExplorePhoto(
    @SerializedName("name") val name: String? = null,
    @SerializedName("descriptionFragment") val descriptionFragment: String? = null,
    @SerializedName("related") val related: List<Tag>? = null
)