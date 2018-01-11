package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.EpoxyController
import com.github.jasonhezz.likesplash.data.ExploreCollection
import com.github.jasonhezz.likesplash.data.model.exploreCollection
import com.github.jasonhezz.likesplash.data.model.exploreCollectionThumb
import com.github.jasonhezz.likesplash.data.model.exploreTitle

/**
 * Created by JavaCoder on 2018/1/10.
 */
class PopularCollectionController() : EpoxyController() {

  var explores = emptyList<ExploreCollection>()
    set(value) {
      field = value
      requestModelBuild()
    }

  override fun buildModels() {
    explores.forEach {
      exploreTitle {
        id(it.name)
        title(it.name)
        description(it.descriptionFragment)
      }
      it.collections?.forEachIndexed { index, collection ->
        if (index < 2) {
          exploreCollection {
            id(collection.id)
            collection(collection)
          }
        } else {
          exploreCollectionThumb {
            id(collection.id)
            collection(collection)
          }
        }
      }
    }
  }
}