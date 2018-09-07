package com.github.jasonhezz.likesplash.ui.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.PreviewPhoto
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.ui.epoxy.withModels
import com.github.jasonhezz.likesplash.util.extension.clearItemDecoration
import com.github.jasonhezz.likesplash.util.recyclerview.SpanGirdItemDecoration
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

    @EpoxyAttribute
    var tagDivider: FlexboxItemDecoration? = null

    @EpoxyAttribute
    var previewImgDivider: SpanGirdItemDecoration? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        collection?.let {
            holder.title_tv.text = it.title
            holder.description_tv.text = "${it.totalPhotos
                    ?: 0} photos · Curated by ${it.user?.name}"
            holder.tag_rv.clearItemDecoration()
            tagDivider?.let { holder.tag_rv.addItemDecoration(it) }
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
            holder.preview_rv.clearItemDecoration()
            previewImgDivider?.let { holder.preview_rv.addItemDecoration(it) }
            holder.preview_rv.withModels {
                val placeholderPhotos = arrayOfNulls<PreviewPhoto>(3)
                it.previewPhotos?.take(3)?.forEachIndexed { index, photo ->
                    placeholderPhotos[index] = photo
                }
                placeholderPhotos.forEachIndexed { index, previewPhoto ->
                    gridImg {
                        id(previewPhoto?.id)
                        photo(previewPhoto)
                        onClickListener(collectionClickListener)
                        spans(if (index == 0) 2 else 1)
                    }
                }
            }
        }
    }
}