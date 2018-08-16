package com.github.jasonhezz.likesplash.data.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.item_photo.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
@EpoxyModelClass(layout = R.layout.item_photo)
abstract class PhotoModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    var photo: Photo? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var avatarClickListener: View.OnClickListener? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var photoClickListener: View.OnClickListener? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        photo?.let {
            val aspectRatio = it.height.toFloat() / it.width.toFloat()
            holder.photo_iv.aspectRatio = aspectRatio
            GlideApp
                .with(holder.photo_iv)
                .saturateOnLoad()
                .load(it.urls?.regular)
                .thumbnail(Glide.with(holder.photo_iv).load(it.urls?.thumb))
                .into(holder.photo_iv)
            GlideApp
                .with(holder.user_avatar)
                .load(it.user?.profile_image?.large)
                .into(holder.user_avatar)
            holder.user_name?.text = it.user?.name
            holder.user_avatar.setOnClickListener(avatarClickListener)
            holder.card.setOnClickListener(photoClickListener)
        }
    }
}
