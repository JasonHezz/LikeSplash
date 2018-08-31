package com.github.jasonhezz.likesplash.ui.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Tag
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import com.github.jasonhezz.likesplash.util.extension.showSnackbar
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
            holder.chip.text = it.title?.capitalize()
            holder.chip.setOnClickListener {
                it.showSnackbar(tag?.title ?: "")
            }
        }
    }
}