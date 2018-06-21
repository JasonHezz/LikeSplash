package com.github.jasonhezz.likesplash.ui.controller

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.ExploreCollection
import com.github.jasonhezz.likesplash.data.model.exploreCollection
import com.github.jasonhezz.likesplash.data.model.exploreCollectionThumb
import com.github.jasonhezz.likesplash.data.model.exploreTitle
import com.github.jasonhezz.likesplash.util.recyclerview.NumItemsInGridRow

/**
 * Created by JavaCoder on 2018/1/10.
 */
class PopularCollectionController(val context: Context) : EpoxyController() {

  var explores = emptyList<ExploreCollection>()
    set(value) {
      field = value
      requestModelBuild()
    }

  override fun buildModels() {
    explores.forEach {
      exploreTitle {
        id(it.name?:"")
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
                NumItemsInGridRow(context, R.integer.grid_explore_collection_thumb_per_row))
          }
        }
      }
    }
  }
}