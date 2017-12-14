package com.github.jasonhezz.likesplash.data.viewholder

import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_title.*

/**
 * Created by JavaCoder on 2017/10/16.
 */
class TitleHolder : BaseViewHolder() {
  fun bind(t: String?) {
    title?.text = t
  }
}