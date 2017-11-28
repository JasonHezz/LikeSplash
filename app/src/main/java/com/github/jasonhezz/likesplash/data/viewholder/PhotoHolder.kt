package com.github.jasonhezz.likesplash.model

import android.graphics.Color
import android.view.View
import com.bumptech.glide.Glide
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.util.extension.hexToMaterialHex
import kotlinx.android.synthetic.main.item_photo.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
class PhotoHolder : BaseViewHolder() {
  fun bind(photo: Photo, avatarListener: View.OnClickListener?,
      cardListener: View.OnClickListener?) {
    val aspectRatio = photo.height.toFloat() / photo.width.toFloat()
    photo_iv.aspectRatio = aspectRatio
    card.setCardBackgroundColor(photo.color.hexToMaterialHex() ?: Color.parseColor("#26292c"))
    Glide.with(photo_iv.context).load(photo.urls?.regular).thumbnail(
        Glide.with(photo_iv.context).load(photo.urls?.thumb)).into(photo_iv)
    Glide.with(user_avatar.context).load(photo.user?.profile_image?.large).into(
        user_avatar)
    user_name?.text = photo.user?.name
    user_avatar.setOnClickListener { avatarListener?.onClick(it) }
    card.setOnClickListener { cardListener?.onClick(it) }
  }
}
