package com.github.jasonhezz.likesplash.util.recyclerview

import android.content.Context
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.annotation.Dimension
import android.support.annotation.Px
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import com.airbnb.epoxy.EpoxyControllerAdapter
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.model.ExplorePhotoModel
import com.github.jasonhezz.likesplash.data.model.ExploreTagModel
import com.github.jasonhezz.likesplash.data.model.ExploreTitleModel

/**
 * Created by JavaCoder on 2018/1/10.
 */
class PopularPhotoDecoration(val context: Context) : RecyclerView.ItemDecoration() {

  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    val position = parent.getChildAdapterPosition(view)
    val adapter = parent.adapter
    if (adapter is EpoxyControllerAdapter) {
      val model = adapter.getModelAtPosition(position)
      //TODO reduce left and right margin in two column or three column in tablet
      when (model::class.java.superclass) {
        ExploreTitleModel::class.java -> outRect.setEmpty()
        ExplorePhotoModel::class.java -> outRect.set(
            resToPx(R.dimen.md_edge_margin), dpToPx(4),
            resToPx(R.dimen.md_edge_margin), dpToPx(4)
        )
        ExploreTagModel::class.java -> outRect.set(
            resToPx(R.dimen.md_edge_margin), dpToPx(4),
            resToPx(R.dimen.md_edge_margin), dpToPx(4)
        )
        else -> outRect.setEmpty()
      }
    } else {
      outRect.setEmpty()
    }
  }

  /*fun isFirstModel(model: EpoxyModel<Any>, position: Int,
      adapter: EpoxyControllerAdapter): Boolean {
    if (position == 0) return true
    if (adapter.getModelAtPosition(position - 1)::class != model::class) return true
    return false
  }

  fun isLastModel(model: EpoxyModel<Any>, position: Int,
      adapter: EpoxyControllerAdapter): Boolean {
    if (position == adapter.itemCount) return true
    if (adapter.getModelAtPosition(position + 1)::class != model::class) return true
    return false
  }*/

  @Px
  fun dpToPx(@Dimension(unit = Dimension.DP) dp: Int): Int {
    return TypedValue
        .applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            context.resources.displayMetrics
        )
        .toInt()
  }

  @Px
  fun resToPx(@DimenRes itemSpacingRes: Int): Int {
    return context.resources.getDimensionPixelOffset(itemSpacingRes)
  }
}