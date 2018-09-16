package com.github.jasonhezz.likesplash.ui.epoxy.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.item_related_collection.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
@EpoxyModelClass(layout = R.layout.item_related_collection)
abstract class RelatedCollectionModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    var collection: Collection? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        collection?.let {
            val count = it.totalPhotos ?: 0
            holder.count_tv.text = holder.count_tv.context.resources
                    .getQuantityString(R.plurals.photo_plural, count, count)
            holder.title_tv.text = it.title
            holder.user_name.text = it.user?.name
            GlideApp
                    .with(holder.collection_iv)
                    .saturateOnLoad()
                    .load(it.coverPhoto?.urls?.regular)
                    .thumbnail(Glide.with(holder.user_avatar.context).load(it.coverPhoto?.urls?.thumb))
                    .materialPlaceHolder(it.coverPhoto?.color ?: "#26292c")
                    .into(holder.collection_iv)
            GlideApp.with(holder.user_avatar)
                    .load(it.user?.profile_image?.medium)
                    .into(holder.user_avatar)
        }
    }
}