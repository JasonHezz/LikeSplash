package com.github.jasonhezz.likesplash.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.github.jasonhezz.likesplash.util.extension.clearItemDecoration
import com.google.android.flexbox.FlexboxLayoutManager


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
internal class FlexCarousel(context: Context) : Carousel(context) {

    @ModelProp
    @JvmField
    var isFullSpan = false

    @ModelProp(ModelProp.Option.DoNotHash)
    fun addItemDecoration(decorationBuilder: (context: Context) -> ItemDecoration) {
        clearItemDecoration()
        addItemDecoration(decorationBuilder(context))
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager {
        return FlexboxLayoutManager(context)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (layoutParams is StaggeredGridLayoutManager.LayoutParams && isFullSpan) {
            (layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
        }
    }
}