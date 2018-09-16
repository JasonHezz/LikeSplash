package android.support.design.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.util.extension.AppBarStateChangeListener
import com.github.jasonhezz.likesplash.util.extension.State

class BadgeBehavior @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior<View>(context, attrs) {
    private var fraction = 0f
    private var appbarChangeListener: AppBarStateChangeListener? = null

    override fun layoutDependsOn(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, dependency: View): Boolean {
        if (dependency is com.google.android.material.appbar.AppBarLayout) {
            return true
        }
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun onLayoutChild(
            parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View,
            layoutDirection: Int
    ): Boolean {
        val appbarLayout = parent.findViewById<com.google.android.material.appbar.AppBarLayout?>(R.id.app_bar_layout)
        if (appbarChangeListener == null) {
            appbarChangeListener = object : AppBarStateChangeListener() {
                override fun onStateChanged(appBarLayout: com.google.android.material.appbar.AppBarLayout, state: State) {}

                override fun onOffsetChanged(state: State, fraction: Float) {
                    this@BadgeBehavior.fraction = fraction
                }
            }
            appbarLayout?.addOnOffsetChangedListener(appbarChangeListener)
        }
        return super.onLayoutChild(parent, child, layoutDirection)
    }


    override fun onDependentViewChanged(parent: androidx.coordinatorlayout.widget.CoordinatorLayout, child: View, dependency: View): Boolean {
        scaleBadge(child)
        return super.onDependentViewChanged(parent, child, dependency)
    }

    private fun scaleBadge(child: View) {
        child.scaleX = 1 - fraction
        child.scaleY = 1 - fraction
    }
}