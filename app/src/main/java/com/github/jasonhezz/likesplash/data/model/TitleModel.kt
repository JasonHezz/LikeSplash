package com.github.jasonhezz.likesplash.data.model

import android.support.v7.widget.StaggeredGridLayoutManager
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

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int = totalSpanCount

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        holder.title?.text = title
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        val layoutParams = holder.containerView?.layoutParams
        if (layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            layoutParams.isFullSpan = true
        }
    }
}