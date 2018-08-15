package com.github.jasonhezz.likesplash.data.model

import android.support.v7.widget.StaggeredGridLayoutManager
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.infinite_loading.*

/**
 * Created by JavaCoder on 2017/11/28.
 */
@EpoxyModelClass(layout = R.layout.infinite_loading)
abstract class LoadingModel : EpoxyModelWithHolder<BaseViewHolder>() {

    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int = totalSpanCount

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        val layoutParams = holder.loading.layoutParams
        if (layoutParams != null && layoutParams is StaggeredGridLayoutManager.LayoutParams) {
            layoutParams.isFullSpan = true
        }
    }
}