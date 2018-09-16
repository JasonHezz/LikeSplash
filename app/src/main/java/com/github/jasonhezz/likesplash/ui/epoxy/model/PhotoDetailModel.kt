package com.github.jasonhezz.likesplash.ui.epoxy.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
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
            GlideApp
                    .with(holder.photo_iv)
                    .load(it.urls?.regular)
                    .into(holder.photo_iv)
        }
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        val layoutParams = holder.containerView?.layoutParams
        if (layoutParams is androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams) {
            layoutParams.isFullSpan = true
        }
    }
}
