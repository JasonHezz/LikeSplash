package com.github.jasonhezz.likesplash.util.recyclerview

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import com.arasthel.spannedgridlayoutmanager.SpanLayoutParams
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager

class SpanGirdItemDecoration(private var divider: Drawable) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
        super.onDraw(c, parent, state)
        drawHorizontal(c, parent)
        drawVertical(c, parent)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val adapterPosition = parent.getChildAdapterPosition(view)
        when (adapterPosition) {
            0 -> outRect.set(0, 0, divider.intrinsicWidth, 0)
            1 -> outRect.set(0, 0, 0, divider.intrinsicHeight)
            else -> outRect.set(0, 0, 0, 0)
        }
        /*val spanCount = getSpanCount(parent)
        val isLastColumn = isLastColumn(parent, view, spanCount)
        val isLastRow = isLastRow(parent, view, spanCount)
        when {
            (isLastColumn && isLastRow) -> outRect.set(0, 0, 0, 0)
            isLastColumn -> outRect.set(0, 0, 0, divider.intrinsicHeight)
            isLastRow -> outRect.set(0, 0, divider.intrinsicWidth, 0)
            else -> outRect.set(0, 0, divider.intrinsicWidth, divider.intrinsicHeight)
        }*/
    }

    private fun getSpanCount(parent: androidx.recyclerview.widget.RecyclerView): Int {
        // 列数
        val layoutManager = parent.layoutManager
        if (layoutManager is SpannedGridLayoutManager) {

            return layoutManager.spans
        }
        throw RuntimeException("SpanGirdItemDecoration only support SpannedGridLayoutManager")
    }

    private fun drawHorizontal(c: Canvas, parent: androidx.recyclerview.widget.RecyclerView) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams
            val left = child.left - params.leftMargin
            val right = (child.right + params.rightMargin + divider.intrinsicWidth)
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }

    private fun drawVertical(c: Canvas, parent: androidx.recyclerview.widget.RecyclerView) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams
            val top = child.top - params.topMargin
            val bottom = child.bottom + params.bottomMargin
            val left = child.right + params.rightMargin
            val right = left + divider.intrinsicWidth
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
    }

    private fun isLastRow(parent: androidx.recyclerview.widget.RecyclerView, view: View, spanCount: Int): Boolean {
        val totalHeightSpan = getTotalHeightSpan(parent, spanCount)
        val childHeightSpan = getSpanGroupHeightIndex(parent, view, spanCount)
        if (childHeightSpan == totalHeightSpan) return true
        return false
    }

    private fun isLastColumn(parent: androidx.recyclerview.widget.RecyclerView, view: View, spanCount: Int): Boolean {
        val totalChildSpan = getSpanGroupWidthIndex(parent, view, spanCount)
        if (totalChildSpan == spanCount) return true
        return false
    }

    private fun getTotalHeightSpan(parent: androidx.recyclerview.widget.RecyclerView, spanCount: Int): Int {
        var span = 0
        var groupIndex = -1
        for (i in 0 until parent.childCount) {
            val index = getSpanGroupHeightIndex(parent, parent.getChildAt(i), spanCount)
            if (index != groupIndex) {
                val size = getSpanHeightSize(parent.getChildAt(i))
                groupIndex = index
                span += size
            }
        }
        return span
    }

    private fun getSpanGroupWidthIndex(parent: androidx.recyclerview.widget.RecyclerView, child: View, spanCount: Int): Int {
        var span = 0
        val positionSpanSize = getSpanWidthSize(child)
        val adapterPosition = parent.getChildAdapterPosition(child)
        var groupHeightIndex = getSpanGroupHeightIndex(parent, child, spanCount)
        for (i in 0 until adapterPosition) {
            val childHeightIndex = getSpanGroupHeightIndex(parent, parent.getChildAt(i), spanCount)
            if (childHeightIndex == groupHeightIndex) {
                val size = getSpanHeightSize(parent.getChildAt(i))
                groupHeightIndex = childHeightIndex
                span += size
            }
        }
        span += positionSpanSize
        return span
    }

    /**
     * FIX ME 计算跨列错误
     */
    private fun getSpanGroupHeightIndex(parent: androidx.recyclerview.widget.RecyclerView, child: View, spanCount: Int): Int {
        var span = 0
        var group = 0
        val positionSpanSize = getSpanWidthSize(child)
        val adapterPosition = parent.getChildAdapterPosition(child)
        for (i in 0 until adapterPosition) {
            val widthSize = getSpanWidthSize(parent.getChildAt(i))
            span += widthSize
            if (span == spanCount) {
                span = 0
                ++group
            } else if (span > spanCount) {
                span = widthSize
                ++group
            }
        }
        if (span + positionSpanSize > spanCount) {
            ++group
        }
        return group
    }

    private fun getSpanWidthSize(child: View): Int {
        val layoutParam = child.layoutParams
        if (layoutParam is SpanLayoutParams) {
            return layoutParam.spanSize.width
        }
        throw RuntimeException("SpanGirdItemDecoration only support SpannedGridLayoutManager")
    }

    private fun getSpanHeightSize(child: View): Int {
        val layoutParam = child.layoutParams
        if (layoutParam is SpanLayoutParams) {
            return layoutParam.spanSize.height
        }
        throw RuntimeException("SpanGirdItemDecoration only support SpannedGridLayoutManager")
    }
}