package com.github.jasonhezz.likesplash.ui.epoxy.controller

import android.view.View
import com.airbnb.epoxy.AsyncEpoxyController
import com.github.jasonhezz.likesplash.data.entities.SearchHints
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.model.searchHint

class SearchHintController(var callback: AdapterCallbacks? = null) : AsyncEpoxyController() {
    var data: SearchHints? by EpoxyModelProperty { null }
    var query: String by EpoxyModelProperty { "" }

    override fun buildModels() {
        data?.autocomplete?.forEachIndexed { index, searchHint ->
            searchHint {
                id(index)
                hint(searchHint.query)
                query(query)
                clickListener(View.OnClickListener { callback?.onClick(searchHint.query) })
            }
        }
    }

    interface AdapterCallbacks {
        fun onClick(it: String?)
    }
}