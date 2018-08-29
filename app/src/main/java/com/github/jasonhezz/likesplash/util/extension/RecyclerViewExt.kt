package com.github.jasonhezz.likesplash.util.extension

import android.support.v7.widget.RecyclerView

/**
 * Created by JavaCoder on 2017/11/15.
 */

fun RecyclerView.clearItemDecoration() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}