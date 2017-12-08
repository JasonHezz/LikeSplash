package com.github.jasonhezz.likesplash.data.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Tag
import com.github.jasonhezz.likesplash.data.viewholder.TagHolder

/**
 * Created by JavaCoder on 2017/10/18.
 */
@EpoxyModelClass(layout = R.layout.item_tag)
abstract class TagModel : EpoxyModelWithHolder<TagHolder>() {
  @EpoxyAttribute
  var tag: Tag? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var tagClickListener: View.OnClickListener? = null

  override fun bind(holder: TagHolder?) {
    super.bind(holder)
    tag?.let { holder?.bind(it, tagClickListener) }
  }
}