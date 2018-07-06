package com.github.jasonhezz.likesplash.data.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Photo
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
            holder.user_name.text = it.user?.name
            GlideApp.with(holder.photo_iv.context)
                .load(it.urls?.regular)
                .thumbnail(Glide.with(holder.user_avatar.context).load(it.urls?.thumb))
                .materialPlaceHolder(it.color)
                .into(holder.photo_iv)
            GlideApp.with(holder.user_avatar.context)
                .load(it.user?.profile_image?.large)
                .placeholder(R.drawable.avatar_placeholder)
                .into(holder.user_avatar)
        }
    }
}
