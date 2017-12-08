package com.github.jasonhezz.likesplash.data.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.github.jasonhezz.likesplash.data.Tag
import kotlinx.android.synthetic.main.item_tag.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
class LoadingModelHolder : BaseViewHolder() {

  fun bind(tag: Tag, tagClickListener: View.OnClickListener?) {
    tag_name.text = tag.title?.capitalize()
    Glide.with(tag_thumbnail?.context).load(tag.url).into(tag_thumbnail)
    card.setOnClickListener { tagClickListener?.onClick(it) }
  }
}