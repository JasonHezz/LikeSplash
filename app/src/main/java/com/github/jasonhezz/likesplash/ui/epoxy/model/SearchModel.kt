package com.github.jasonhezz.likesplash.ui.epoxy.model

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_search.*

@EpoxyModelClass(layout = R.layout.item_search)
abstract class SearchModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    var serach: String? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        holder.tv.text = serach
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}