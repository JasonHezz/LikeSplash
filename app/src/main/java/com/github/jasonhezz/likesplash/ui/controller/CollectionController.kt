package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.model.collection

/**
 * Created by JavaCoder on 2017/10/16.
 */

class CollectionController(
    var callback: AdapterCallbacks? = null) : TypedEpoxyController<List<Collection>>() {


  override fun buildModels(collections: List<Collection>?) {

    collections?.forEach {
      collection {
        id(it.id)
        collection(it)
        avatarClickListener { model, parentView, clickedView, position ->
          callback?.onAvatarClick()
        }
        collectionClickListener { model, parentView, clickedView, position ->
          callback?.onCollectionClick()
        }
      }
    }
  }

  interface AdapterCallbacks {
    fun onAvatarClick()
    fun onCollectionClick()
  }
}

