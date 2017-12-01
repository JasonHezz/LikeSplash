package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.EpoxyController
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.model.LoadingModel_
import com.github.jasonhezz.likesplash.data.model.photo

/**
 * Created by JavaCoder on 2017/10/16.
 */

class PhotoController(
    var callback: AdapterCallbacks? = null) : EpoxyController() {

  @AutoModel
  lateinit var loadingModel: LoadingModel_

  var photos = emptyList<Photo>()
    set(value) {
      field = value
      requestModelBuild()
    }

  var isLoading: Boolean = false
    set(value) {
      field = value
      requestModelBuild()
    }

  override fun buildModels() {
    photos.forEach {
      photo {
        id(it.id)
        photo(it)
        avatarClickListener { model, parentView, clickedView, position ->
          callback?.onAvatarClick(it.user)
        }
        photoClickListener { model, parentView, clickedView, position ->
          callback?.onPhotoClick()
        }
      }
    }
    loadingModel.addIf(isLoading, this)
  }

  interface AdapterCallbacks {
    fun onAvatarClick(id: User?)
    fun onPhotoClick()
  }
}
