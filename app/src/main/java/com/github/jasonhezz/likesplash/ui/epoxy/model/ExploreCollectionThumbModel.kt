package com.github.jasonhezz.likesplash.ui.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.item_explore_collection.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
@EpoxyModelClass(layout = R.layout.item_explore_collection_thumbnail)
abstract class ExploreCollectionThumbModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    var collection: Collection? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var collectionClickListener: View.OnClickListener? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        collection?.let {

            val count = it.totalPhotos ?: 0
            holder.count_tv.text =
                    holder.count_tv.context.resources.getQuantityString(R.plurals.photo_plural, count, count)
            holder.title_tv.text = it.title
            GlideApp
                    .with(holder.collection_iv)
                    .load(it.coverPhoto?.urls?.regular)
                    .into(holder.collection_iv)
            holder.card.setOnClickListener(collectionClickListener)
        }
    }
}