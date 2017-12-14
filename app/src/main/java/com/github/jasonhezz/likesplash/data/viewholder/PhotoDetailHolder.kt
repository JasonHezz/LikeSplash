package com.github.jasonhezz.likesplash.data.viewholder

import com.bumptech.glide.Glide
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.extension.hexToMaterialHex
import com.github.jasonhezz.likesplash.util.extension.showSnackbar
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.item_photo_detail.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
class PhotoDetailHolder : BaseViewHolder() {
  fun bind(photo: Photo) {
    val aspectRatio = photo.height.toFloat() / photo.width.toFloat()
    photo_iv.aspectRatio = aspectRatio
    card.setCardBackgroundColor(photo.color.hexToMaterialHex())
    user_name.text = photo.user?.name
    GlideApp.with(photo_iv.context).load(photo.urls?.regular).thumbnail(
        Glide.with(user_avatar.context).load(photo.urls?.thumb)).into(photo_iv)
    GlideApp.with(user_avatar.context).load(photo.user?.profile_image?.large).placeholder(
        R.drawable.avatar_placeholder).into(user_avatar)
    photo_iv.setOnClickListener { it.showSnackbar(it.toString()) }
  }
}