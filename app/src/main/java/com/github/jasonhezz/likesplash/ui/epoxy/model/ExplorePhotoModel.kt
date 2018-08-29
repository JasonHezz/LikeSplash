package com.github.jasonhezz.likesplash.ui.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.item_explore_photo.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
@EpoxyModelClass(layout = R.layout.item_explore_photo)
abstract class ExplorePhotoModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    var photo: Photo? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var photoClickListener: View.OnClickListener? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        if (photo != null) {
            GlideApp
                .with(holder.photo_iv)
                .saturateOnLoad()
                .load(photo?.urls?.regular)
                .thumbnail(Glide.with(holder.photo_iv.context).load(photo?.urls?.thumb))
                .into(holder.photo_iv)
            holder.photo_iv.setOnClickListener(photoClickListener)
        }
    }
}
