package com.github.jasonhezz.likesplash.model

import android.graphics.Color
import android.view.View
import com.bumptech.glide.Glide
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.extension.hexToMaterialHex
import kotlinx.android.synthetic.main.item_collection.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
class CollectionHolder : BaseViewHolder() {

  fun bind(collection: Collection, avatarListener: View.OnClickListener?,
      cardListener: View.OnClickListener?) {
    if (collection.cover_photo?.height != null && collection.cover_photo?.width != null) {
      val aspectRatio = collection.cover_photo?.height?.toFloat()!! / collection.cover_photo?.width?.toFloat()!!
      collection_iv.aspectRatio = aspectRatio
    }
    val count = collection.total_photos ?: 0
    card.setCardBackgroundColor(
        collection.cover_photo?.color?.hexToMaterialHex() ?: Color.parseColor("#26292c"))
    count_tv.text = count_tv.context.resources.getQuantityString(R.plurals.photo_plural, count,
        count)
    title_tv.text = collection.title
    user_name.text = collection.user?.name
    Glide.with(collection_iv.context).load(collection.cover_photo?.urls?.regular).thumbnail(
        Glide.with(user_avatar.context).load(collection.cover_photo?.urls?.thumb)).into(
        collection_iv)
    Glide.with(user_avatar.context).load(collection.user?.profile_image?.medium).into(user_avatar)
    card.setOnClickListener { cardListener?.onClick(it) }
    user_avatar.setOnClickListener { avatarListener?.onClick(it) }
  }
}