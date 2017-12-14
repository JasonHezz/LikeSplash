package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.model.collection

/**
 * Created by JavaCoder on 2017/10/16.
 */

class CollectionController : TypedEpoxyController<List<Collection>>() {

  var onAvatarClick: (() -> Unit)? = null
  var onCollectionClick: (() -> Unit)? = null

  override fun buildModels(collections: List<Collection>?) {

    collections?.forEach {
      collection {
        id(it.id)
        collection(it)
        avatarClickListener { model, parentView, clickedView, position ->
          onAvatarClick?.invoke()
        }
        collectionClickListener { model, parentView, clickedView, position ->
          onCollectionClick?.invoke()
        }
      }
    }
  }
}

