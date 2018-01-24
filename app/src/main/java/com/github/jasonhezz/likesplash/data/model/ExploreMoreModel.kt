package com.github.jasonhezz.likesplash.data.model

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Tag
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.item_explore_tag.*

/**
 * Created by JavaCoder on 2017/10/18.
 */
@EpoxyModelClass(layout = R.layout.item_explore_tag)
abstract class ExploreMoreModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var moreClickListener: View.OnClickListener? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        holder.tag_name.text = "View More"
        GlideApp.with(holder.tag_thumbnail?.context)
                .load("")
                .placeholder(ColorDrawable(Color.parseColor("#bdbdbd")))
                .into(holder.tag_thumbnail)
        holder.card.setOnClickListener(moreClickListener)
    }
}