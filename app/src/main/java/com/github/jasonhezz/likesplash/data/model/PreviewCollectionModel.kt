package com.github.jasonhezz.likesplash.data.model

import android.support.v4.content.ContextCompat
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.extension.withModels
import com.google.android.flexbox.FlexboxItemDecoration
import kotlinx.android.synthetic.main.item_preview.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
@EpoxyModelClass(layout = R.layout.item_preview)
abstract class PreviewCollectionModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    var collection: Collection? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var chipClickListener: View.OnClickListener? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        collection?.let {
            holder.title_tv.text = it.title
            holder.description_tv.text = "${it.total_photos
                    ?: 0} photos · Curated by ${it.user?.name}"
            holder.tag_rv.withModels {
                it.tags?.take(3)?.forEachIndexed { index, tag ->
                    chip {
                        id("${collection?.id} $index")
                        tag(tag)
                        tagClickListener(chipClickListener)
                    }
                }
            }
            holder.preview_rv.layoutManager = SpannedGridLayoutManager(
                    orientation = SpannedGridLayoutManager.Orientation.VERTICAL,
                    spans = 3)
            holder.preview_rv.withModels {
                it.preview_photos?.take(3)?.forEachIndexed { index, photo ->
                    gridImg {
                        id(photo.id)
                        photo(photo)
                        spans(if (index == 0) 2 else 1)
                    }
                }

            }
        }
    }
}