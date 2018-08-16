package com.github.jasonhezz.likesplash.data

import com.github.jasonhezz.likesplash.data.entities.Photo


data class TrendingFeed(
    var next_page: String?,
    var photos: List<Photo>?
)

data class SearchPhotoResult(
    var total: Int?,
    var total_pages: Int?,
    var results: List<Photo>?
)

