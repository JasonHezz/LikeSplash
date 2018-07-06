package com.github.jasonhezz.likesplash.data.model

import android.view.View
import androidx.view.isVisible
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.item_explore_title.*

/**
 * Created by JavaCoder on 2017/10/18.
 */
@EpoxyModelClass(layout = R.layout.item_explore_title)
abstract class ExploreTitleModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    var title: String? = null

    @EpoxyAttribute
    var description: String? = null

    @EpoxyAttribute
    var showViewMore: Boolean = true

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var moreClickListener: View.OnClickListener? = null

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        holder.title.text = title
        holder.description.text = description
        holder.more_btn.isVisible = showViewMore
        holder.more_btn.setOnClickListener(moreClickListener)
    }

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }
}