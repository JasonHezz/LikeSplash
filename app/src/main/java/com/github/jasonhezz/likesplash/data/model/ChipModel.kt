package com.github.jasonhezz.likesplash.data.model

import android.view.View
import android.widget.Toast
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Tag
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_chip.*

/**
 * Created by JavaCoder on 2017/10/18.
 */
@EpoxyModelClass(layout = R.layout.item_chip)
abstract class ChipModel : EpoxyModelWithHolder<BaseViewHolder>() {
  @EpoxyAttribute
  var tag: Tag? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var tagClickListener: View.OnClickListener? = null

  override fun bind(holder: BaseViewHolder) {
    super.bind(holder)
    tag?.let {
      holder.chip.chipText = it.title
      //holder.chip.setOnClickListener(tagClickListener)
      holder.chip.setOnClickListener {
        Toast.makeText(it.context, tag?.title, Toast.LENGTH_SHORT).show()
      }
    }
  }
}