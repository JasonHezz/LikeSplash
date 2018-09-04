package com.github.jasonhezz.likesplash.ui.epoxy.controller

import android.content.Context
import com.airbnb.epoxy.AsyncEpoxyController
import com.github.jasonhezz.likesplash.data.entities.SearchHints
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.model.search

/**
 * Created by JavaCoder on 2018/1/10.
 */
class SearchController(val context: Context) : AsyncEpoxyController() {

    var data: SearchHints? by EpoxyModelProperty { null }

    override fun buildModels() {
        data?.fuzzy?.forEach { model ->
            search {
                id(model.priority)
                serach(model.query)
            }
        }
        data?.autocomplete?.forEach { model ->
            search {
                id(model.priority)
                serach(model.query)
            }
        }
        data?.did_you_mean?.forEach { model ->
            search {
                id(model.priority)
                serach(model.query)
            }
        }
    }
}