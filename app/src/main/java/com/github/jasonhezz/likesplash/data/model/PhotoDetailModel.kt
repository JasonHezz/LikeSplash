package com.github.jasonhezz.likesplash.data.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.model.PhotoDetailHolder

/**
 * Created by JavaCoder on 2017/10/24.
 */
@EpoxyModelClass(layout = R.layout.item_photo_detail)
abstract class PhotoDetailModel : EpoxyModelWithHolder<PhotoDetailHolder>() {

  @EpoxyAttribute
  var photo: Photo? = null

  override fun bind(holder: PhotoDetailHolder?) {
    super.bind(holder)
    photo?.let { holder?.bind(it) }
  }
}
