package com.github.jasonhezz.likesplash.data.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_explore_more.*

/**
 * Created by JavaCoder on 2018/1/24.
 */
@EpoxyModelClass(layout = R.layout.item_explore_more)
abstract class ExploreMoreModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var clickListener: View.OnClickListener? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        holder.card.setOnClickListener(clickListener)
    }
}