package com.github.jasonhezz.likesplash.data.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_title.*

/**
 * Created by JavaCoder on 2017/10/20.
 */
@EpoxyModelClass(layout = R.layout.item_title)
abstract class TitleModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    var title: String? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        holder.title?.text = title
    }
}