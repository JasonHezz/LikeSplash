package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.EpoxyController
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.model.LoadingModel_
import com.github.jasonhezz.likesplash.data.model.collection

/**
 * Created by JavaCoder on 2017/10/16.
 */

class CollectionController(
    var callback: AdapterCallbacks? = null) : EpoxyController() {
  @AutoModel
  lateinit var loadingModel: LoadingModel_

  var isLoading: Boolean = false
    set(value) {
      field = value
      requestModelBuild()
    }


  var collections = emptyList<Collection>()
    set(value) {
      if (field != value) {
        field = value
        requestModelBuild()
      }
    }

  override fun buildModels() {
    collections.forEach {
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
    loadingModel.addIf(isLoading, this)
  }

  interface AdapterCallbacks {
    fun onAvatarClick()
    fun onCollectionClick()
  }
}

