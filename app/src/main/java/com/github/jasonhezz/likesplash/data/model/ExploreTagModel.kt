package com.github.jasonhezz.likesplash.data.model

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Tag
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.item_explore_tag.*

/**
 * Created by JavaCoder on 2017/10/18.
 */
@EpoxyModelClass(layout = R.layout.item_explore_tag)
abstract class ExploreTagModel : EpoxyModelWithHolder<BaseViewHolder>() {
  @EpoxyAttribute
  var tag: Tag? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var tagClickListener: View.OnClickListener? = null

  override fun bind(holder: BaseViewHolder) {
    super.bind(holder)
    tag?.let {
      holder.tag_name.text = it.title?.capitalize()
      GlideApp.with(holder.tag_thumbnail?.context)
          .load(it.url)
          .placeholder(ColorDrawable(Color.parseColor("#bdbdbd")))
          .transition(DrawableTransitionOptions.withCrossFade(500))
          .into(holder.tag_thumbnail)
      holder.card.setOnClickListener(tagClickListener)
    }
  }
}