package com.github.jasonhezz.likesplash.view

import android.content.Context
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.github.jasonhezz.likesplash.R
import kotlinx.android.synthetic.main.search_bar.view.*

/**
 * Created by JasonHezz on 2017/9/20.
 */
class SearchBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : CardView(context, attrs, defStyleAttr) {

    private val arrowDrawable: DrawerArrowDrawable = DrawerArrowDrawable(context)
    var onNavigationOnClickListener: ((Float) -> Unit)? = null
    var onBarClickListener: ((View) -> Unit)? = null
    var progress
        get() = arrowDrawable.progress
        set(value) {
            arrowDrawable.progress = value
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.search_bar, this, true)
        search_nav.setImageDrawable(arrowDrawable)
        search_bar.setOnClickListener { onBarClickListener?.invoke(it) }
        search_nav.setOnClickListener {
            onNavigationOnClickListener?.invoke(arrowDrawable.progress)
        }
    }
}