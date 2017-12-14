package com.github.jasonhezz.likesplash.data.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.viewholder.PhotoHolder


/**
 * Created by JavaCoder on 2017/10/16.
 */
@EpoxyModelClass(layout = R.layout.item_photo)
abstract class PhotoModel : EpoxyModelWithHolder<PhotoHolder>() {

  @EpoxyAttribute
  var photo: Photo? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var avatarClickListener: View.OnClickListener? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var photoClickListener: View.OnClickListener? = null

  override fun bind(holder: PhotoHolder?) {
    super.bind(holder)
    photo?.let {
      holder?.bind(it, avatarClickListener, photoClickListener)
    }
  }
}
