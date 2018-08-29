package com.github.jasonhezz.likesplash.ui.epoxy.controller

import android.content.Context
import com.airbnb.epoxy.AsyncEpoxyController
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.ExploreCollection
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.NumItemsInGridRow
import com.github.jasonhezz.likesplash.ui.epoxy.model.exploreCollection
import com.github.jasonhezz.likesplash.ui.epoxy.model.exploreCollectionThumb
import com.github.jasonhezz.likesplash.ui.epoxy.model.exploreTitle

/**
 * Created by JavaCoder on 2018/1/10.
 */
class PopularCollectionController(val context: Context) : AsyncEpoxyController() {

    var data: List<ExploreCollection>? by EpoxyModelProperty { null }

    override fun buildModels() {
        data?.forEach {
            exploreTitle {
                id(it.name ?: "error")
                title(it.name)
                description(it.descriptionFragment)
            }
            it.collections?.forEachIndexed { index, collection ->
                if (index < 2) {
                    exploreCollection {
                        id(collection.id)
                        collection(collection)
                        spanSizeOverride(NumItemsInGridRow(context, R.integer.grid_explore_collection_per_row))
                    }
                } else {
                    exploreCollectionThumb {
                        id(collection.id)
                        collection(collection)
                        spanSizeOverride(
                            NumItemsInGridRow(context, R.integer.grid_explore_collection_thumb_per_row)
                        )
                    }
                }
            }
        }
    }
}