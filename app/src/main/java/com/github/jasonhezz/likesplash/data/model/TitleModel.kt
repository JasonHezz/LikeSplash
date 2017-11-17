package com.github.jasonhezz.likesplash.data.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.model.TitleHolder


/**
 * Created by JavaCoder on 2017/10/20.
 */
@EpoxyModelClass(layout = R.layout.item_title)
abstract class TitleModel : EpoxyModelWithHolder<TitleHolder>() {

  @EpoxyAttribute
  var title: String? = null

  override fun bind(holder: TitleHolder?) {
    super.bind(holder)
    holder?.bind(title)
  }
}