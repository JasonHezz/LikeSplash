package com.github.jasonhezz.likesplash.util.extension

/**
 * Created by JavaCoder on 2017/11/15.
 */

fun androidx.recyclerview.widget.RecyclerView.clearItemDecoration() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}