package com.github.jasonhezz.likesplash.data.model

import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.HORIZONTAL
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager
import com.github.jasonhezz.likesplash.App
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.extension.clearItemDecoration
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

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var collectionClickListener: View.OnClickListener? = null

    val tagDivider = FlexboxItemDecoration(App.applicationContext()).apply {
        setDrawable(
            ContextCompat.getDrawable(App.applicationContext(), R.drawable.chip_divider)
        )
    }

    val previewImagHDivider = DividerItemDecoration(App.applicationContext(), HORIZONTAL).apply {
        setDrawable(ContextCompat.getDrawable(App.applicationContext(), R.drawable.preview_img_divider)!!)
    }

    val previewImagVDivider = DividerItemDecoration(App.applicationContext(), VERTICAL).apply {
        setDrawable(ContextCompat.getDrawable(App.applicationContext(), R.drawable.preview_img_divider)!!)
    }

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        collection?.let {
            holder.title_tv.text = it.title
            holder.description_tv.text = "${it.totalPhotos
                ?: 0} photos · Curated by ${it.user?.name}"
            while (holder.tag_rv.itemDecorationCount > 0) {
                holder.tag_rv.removeItemDecorationAt(0)
            }
            holder.tag_rv.clearItemDecoration()
            holder.tag_rv.addItemDecoration(tagDivider)
            holder.tag_rv.withModels {
                it.tags?.take(3)
                    ?.forEachIndexed { index, tag ->
                        chip {
                            id("${collection?.id} $index")
                            tag(tag)
                            tagClickListener(chipClickListener)
                        }
                    }
            }
            holder.preview_rv.layoutManager = SpannedGridLayoutManager(
                orientation = SpannedGridLayoutManager.Orientation.VERTICAL,
                spans = 3
            )
            holder.preview_rv.setOnClickListener(collectionClickListener)
            holder.preview_rv.clearItemDecoration()
            holder.preview_rv.addItemDecoration(previewImagHDivider)
            holder.preview_rv.addItemDecoration(previewImagVDivider)
            holder.preview_rv.withModels {
                it.previewPhotos?.take(3)
                    ?.forEachIndexed { index, photo ->
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