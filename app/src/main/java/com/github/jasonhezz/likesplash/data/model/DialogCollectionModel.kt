package com.github.jasonhezz.likesplash.data.model

import android.view.View
import androidx.view.isVisible
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.item_dialog_collection.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
@EpoxyModelClass(layout = R.layout.item_dialog_collection)
abstract class DialogCollectionModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    var collection: Collection? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var collectionClickListener: View.OnClickListener? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        collection?.let {
            val count = it.total_photos ?: 0
            holder.is_private.isVisible = collection?.private ?: false
            holder.count_tv.text = holder.count_tv.context.resources.getQuantityString(
                R.plurals.photo_plural, count, count
            )
            holder.title_tv.text = it.title
            GlideApp.with(holder.collection_iv.context).load(it.cover_photo?.urls?.regular)
                .into(holder.collection_iv)
            holder.card.setOnClickListener(collectionClickListener)
        }
    }
}