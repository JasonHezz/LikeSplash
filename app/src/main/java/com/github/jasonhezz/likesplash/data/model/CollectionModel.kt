package com.github.jasonhezz.likesplash.data.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.item_collection.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
@EpoxyModelClass(layout = R.layout.item_collection)
abstract class CollectionModel : EpoxyModelWithHolder<BaseViewHolder>() {

  @EpoxyAttribute
  var collection: Collection? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var avatarClickListener: View.OnClickListener? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var collectionClickListener: View.OnClickListener? = null

  override fun bind(holder: BaseViewHolder) {
    super.bind(holder)
    collection?.let {
      if (it.cover_photo?.height != null && it.cover_photo?.width != null) {
        val aspectRatio = it.cover_photo?.height?.toFloat()!! / it.cover_photo?.width?.toFloat()!!
        holder.collection_iv.aspectRatio = aspectRatio
      }
      val count = it.total_photos ?: 0
      holder.count_tv.text = holder.count_tv.context.resources.
          getQuantityString(R.plurals.photo_plural, count, count)
      holder.title_tv.text = it.title
      holder.user_name.text = it.user?.name
      GlideApp.with(holder.collection_iv.context).load(it.cover_photo?.urls?.regular)
          .thumbnail(
              Glide.with(holder.user_avatar.context).load(it.cover_photo?.urls?.thumb))
          .materialPlaceHolder(it.cover_photo?.color ?: "#26292c")
          .into(holder.collection_iv)
      GlideApp.with(holder.user_avatar.context).load(it.user?.profile_image?.medium)
          .into(holder.user_avatar)
      holder.card.setOnClickListener { collectionClickListener }
      holder.user_avatar.setOnClickListener { avatarClickListener }
    }
  }
}