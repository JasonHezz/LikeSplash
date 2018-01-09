package com.github.jasonhezz.likesplash.util.recyclerview

import android.content.Context
import android.support.annotation.IntegerRes
import com.airbnb.epoxy.EpoxyModel


/**
 * Created by JavaCoder on 2018/1/9.
 */
class NumItemsInGridRow(
    context: Context, @IntegerRes span: Int) : EpoxyModel.SpanSizeOverrideCallback {

  private val numItemsForCurrentScreen: Int = context.resources.getInteger(span)

  override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
    if (totalSpanCount % numItemsForCurrentScreen != 0) {
      throw IllegalStateException(
          "Total Span Count of : $totalSpanCount can not evenly fit: $numItemsForCurrentScreen cards per row")
    }

    return totalSpanCount / numItemsForCurrentScreen
  }
}