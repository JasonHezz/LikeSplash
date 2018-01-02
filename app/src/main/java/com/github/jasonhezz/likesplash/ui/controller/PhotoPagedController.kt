package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.AutoModel
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.model.LoadingModel_
import com.github.jasonhezz.likesplash.data.model.photo

/**
 * Created by JavaCoder on 2017/10/16.
 */

class PhotoPagedController : PagingEpoxyController<Photo>() {

  var onAvatarClick: ((id: String?) -> Unit)? = null
  var onPhotoClick: (() -> Unit)? = null

  @AutoModel
  lateinit var loadingModel: LoadingModel_

  var isLoading: Boolean = false
    set(value) {
      if (field != value) {
        field = value
        requestModelBuild()
      }
    }

  override fun buildModels(list: MutableList<Photo>) {
    list.forEach {
      photo {
        id(it.id)
        photo(it)
        avatarClickListener { model, parentView, clickedView, position ->
          onAvatarClick?.invoke(it.user?.id)
        }
        photoClickListener { model, parentView, clickedView, position ->
          onPhotoClick?.invoke()
        }
      }
    }
    loadingModel.addIf(isLoading, this)
  }
}