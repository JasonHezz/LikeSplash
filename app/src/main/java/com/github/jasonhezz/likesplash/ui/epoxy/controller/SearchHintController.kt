package com.github.jasonhezz.likesplash.ui.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.github.jasonhezz.likesplash.data.entities.SearchHints
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.model.searchHint

class SearchHintController : AsyncEpoxyController() {
    var data: SearchHints? by EpoxyModelProperty { null }
    var query: String by EpoxyModelProperty { "" }

    override fun buildModels() {
        data?.autocomplete?.forEachIndexed { index, searchHint ->
            searchHint {
                id(index)
                hint(searchHint.query)
                query(query)
            }
        }
    }
}