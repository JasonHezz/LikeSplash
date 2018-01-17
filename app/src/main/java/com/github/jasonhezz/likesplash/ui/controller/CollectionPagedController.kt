package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.AutoModel
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.model.LoadingModel_
import com.github.jasonhezz.likesplash.data.model.collection

/**
 * Created by JavaCoder on 2017/12/13.
 */
class CollectionPagedController : PagingEpoxyController<Collection>() {

  var onPhotoClick: ((collection: Collection) -> Unit)? = null

  @AutoModel
  lateinit var loadingModel: LoadingModel_

  var isLoading: Boolean = false
    set(value) {
      field = value
      requestModelBuild()
    }

  override fun buildModels(list: MutableList<Collection>) {
    list.forEach {
      collection {
        id(it.id)
        collection(it)
        collectionClickListener { model, parentView, clickedView, position ->
          onPhotoClick?.invoke(it)
        }
      }
    }
    loadingModel.addIf(isLoading, this)
  }
}