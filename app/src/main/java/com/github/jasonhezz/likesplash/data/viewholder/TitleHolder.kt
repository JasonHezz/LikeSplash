package com.github.jasonhezz.likesplash.model

import kotlinx.android.synthetic.main.item_title.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
class TitleHolder : BaseViewHolder() {
  fun bind(t: String?) {
    title?.text = t
  }
}