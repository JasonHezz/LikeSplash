package com.github.jasonhezz.likesplash.data.entities

data class SearchPhotoResponse(
    var total: Int?,
    var total_pages: Int?,
    var results: List<Photo>?
)

