package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.model.photo

/**
 * Created by JavaCoder on 2017/10/16.
 */

class PhotoController(
    var callback: AdapterCallbacks? = null) : TypedEpoxyController<List<Photo>>() {

  override fun buildModels(photos: List<Photo>?) {
    photos?.forEach {
      photo {
        id(it.id)
        photo(it)
        avatarClickListener { model, parentView, clickedView, position ->
          callback?.onAvatarClick(it.user?.id)
        }
        photoClickListener { model, parentView, clickedView, position ->
          callback?.onPhotoClick()
        }
      }
    }
  }

  interface AdapterCallbacks {
    fun onAvatarClick(id: String?)
    fun onPhotoClick()
  }
}
