package com.github.jasonhezz.likesplash.data.viewholder

import android.graphics.Color
import com.bumptech.glide.Glide
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.extension.hexToMaterialHex
import kotlinx.android.synthetic.main.item_related_collection.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
class RelatedCollectionHolder : BaseViewHolder() {

  fun bind(collection: Collection) {
    val count = collection.total_photos ?: 0
    count_tv.text = count_tv.context.resources.getQuantityString(R.plurals.photo_plural, count,
        count)
    title_tv.text = collection.title
    card.setCardBackgroundColor(
        collection.cover_photo?.color?.hexToMaterialHex()?:Color.parseColor("#26292c"))
    card.setCardBackgroundColor(
        Color.parseColor(collection.cover_photo?.color))
    user_name.text = collection.user?.name
    Glide.with(collection_iv.context).load(collection.cover_photo?.urls?.regular).thumbnail(
        Glide.with(user_avatar.context).load(collection.cover_photo?.urls?.thumb)).into(
        collection_iv)
    Glide.with(user_avatar.context).load(collection.user?.profile_image?.medium).into(user_avatar)
  }
}