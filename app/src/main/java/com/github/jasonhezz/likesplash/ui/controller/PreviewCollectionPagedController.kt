package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.paging.PagingEpoxyController
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.model.LoadingModel_
import com.github.jasonhezz.likesplash.data.model.previewCollection

/**
 * Created by JavaCoder on 2017/12/13.
 */
class PreviewCollectionPagedController : PagingEpoxyController<Collection>() {

  @AutoModel
  lateinit var loadingModel: LoadingModel_

  var isLoading: Boolean = false
    set(value) {
      field = value
      requestModelBuild()
    }

  override fun buildModels(list: MutableList<Collection>) {
    list.forEach {
      previewCollection {
        id(it.id)
        collection(it)
      }
    }
    loadingModel.addIf(isLoading, this)
  }
}