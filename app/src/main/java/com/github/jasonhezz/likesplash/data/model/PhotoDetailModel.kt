package com.github.jasonhezz.likesplash.data.model

import android.support.v7.widget.StaggeredGridLayoutManager
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.item_photo_detail.*

/**
 * Created by JavaCoder on 2017/10/24.
 */
@EpoxyModelClass(layout = R.layout.item_photo_detail)
abstract class PhotoDetailModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    var photo: Photo? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        photo?.let {
            val aspectRatio = it.height.toFloat() / it.width.toFloat()
            holder.photo_iv.aspectRatio = aspectRatio
            GlideApp
                .with(holder.photo_iv)
                .load(it.urls?.regular)
                .into(holder.photo_iv)
        }
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        val layoutParams = holder.containerView?.layoutParams
        if (layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            layoutParams.isFullSpan = true
        }
    }
}
