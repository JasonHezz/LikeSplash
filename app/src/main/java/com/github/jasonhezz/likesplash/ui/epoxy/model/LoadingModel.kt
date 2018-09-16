package com.github.jasonhezz.likesplash.ui.epoxy.model

import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder

/**
 * Created by JavaCoder on 2017/11/28.
 */
@EpoxyModelClass(layout = R.layout.infinite_loading)
abstract class LoadingModel : EpoxyModelWithHolder<BaseViewHolder>() {

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int = totalSpanCount

    override fun onViewAttachedToWindow(holder: BaseViewHolder) {
        super.onViewAttachedToWindow(holder)
        val layoutParams = holder.containerView?.layoutParams
        if (layoutParams is androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams) {
            layoutParams.isFullSpan = true
        }
    }
}