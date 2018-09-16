package android.support.design.widget

import android.animation.TimeInterpolator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.graphics.RectF
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.ViewGroupUtils
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.util.extension.AppBarStateChangeListener
import com.github.jasonhezz.likesplash.util.extension.State
import com.google.android.material.animation.AnimationUtils
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/11/1.
 */

class ProfileAvatarBehavior @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : CoordinatorLayout.Behavior<ImageView>(context, attrs) {

    private var expandRec: Rect? = null
    private var collapsedRec: Rect = Rect()
    private val currentBounds = RectF()
    private var fraction = 0f
    private var appbarChangeListener: AppBarStateChangeListener? = null

    override fun layoutDependsOn(
            parent: CoordinatorLayout, child: ImageView,
            dependency: View
    ): Boolean {
        if (dependency is com.google.android.material.appbar.AppBarLayout) {
            return true
        }
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun onDependentViewChanged(
            parent: CoordinatorLayout, child: ImageView,
            dependency: View
    ): Boolean {
        interpolateBounds(fraction)
        calculateAvatarSize(child)
        return true
    }

    override fun onLayoutChild(
            parent: CoordinatorLayout, child: ImageView,
            layoutDirection: Int
    ): Boolean {
        val appbarLayout = parent.findViewById<com.google.android.material.appbar.AppBarLayout?>(R.id.app_bar_layout)
        handleBounds(parent, child)
        if (appbarChangeListener == null) {
            appbarChangeListener = object : AppBarStateChangeListener() {
                override fun onStateChanged(appBarLayout: com.google.android.material.appbar.AppBarLayout, state: State) {}

                override fun onOffsetChanged(state: State, fraction: Float) {
                    this@ProfileAvatarBehavior.fraction = fraction
                }
            }
            appbarLayout?.addOnOffsetChangedListener(appbarChangeListener)
        }
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    private fun handleBounds(
            parent: CoordinatorLayout,
            child: ImageView
    ) {
        findToolbarLogoRec(parent)
        findAvatarRec(parent, child)
    }

    @SuppressLint("RestrictedApi")
    private fun findToolbarLogoRec(viewGroup: ViewGroup) {
        if (collapsedRec.isEmpty) {
            val toolbar = viewGroup.findViewById<Toolbar>(R.id.profile_toolbar)
            (0 until toolbar.childCount).map {
                val childView = toolbar.getChildAt(it)
                if (childView is ImageView) {
                    ViewGroupUtils.getDescendantRect(viewGroup, childView, collapsedRec)
                }
            }
        }
    }

    private fun findAvatarRec(viewGroup: ViewGroup, child: ImageView) {
        child.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            @SuppressLint("RestrictedApi")
            override fun onLayoutChange(
                    p0: View?, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int,
                    p7: Int, p8: Int
            ) {
                if (expandRec == null) {
                    expandRec = Rect()
                    ViewGroupUtils.getDescendantRect(viewGroup, child, expandRec)
                }
                p0?.removeOnLayoutChangeListener(this)
            }
        })
    }

    private fun calculateOffsets(target: View) {
        target.x = currentBounds.left
        target.y = currentBounds.top
    }

    private fun calculateAvatarSize(target: View) {
        if (expandRec != null) {
            target.layoutParams.width = currentBounds.width().toInt()
            target.layoutParams.height = currentBounds.height().toInt()
            target.requestLayout()
            target.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
                override fun onLayoutChange(
                        p0: View?, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int,
                        p7: Int, p8: Int
                ) {
                    calculateOffsets(target)
                    p0?.removeOnLayoutChangeListener(this)
                }
            })
            Timber.d(
                    "left ${collapsedRec.left} right ${collapsedRec.right} top ${collapsedRec.top} bottom ${collapsedRec.bottom}"
            )
        }
    }

    private fun interpolateBounds(fraction: Float) {
        if (expandRec != null) {
            currentBounds.left = lerp(
                    expandRec!!.left.toFloat(), collapsedRec.left.toFloat(),
                    fraction, AnimationUtils.DECELERATE_INTERPOLATOR
            )
            currentBounds.top = lerp(
                    expandRec!!.top.toFloat(), collapsedRec.top.toFloat(),
                    fraction, AnimationUtils.DECELERATE_INTERPOLATOR
            )
            currentBounds.right = lerp(
                    expandRec!!.right.toFloat(), collapsedRec.right.toFloat(),
                    fraction, AnimationUtils.DECELERATE_INTERPOLATOR
            )
            currentBounds.bottom = lerp(
                    expandRec!!.bottom.toFloat(),
                    collapsedRec.bottom.toFloat(),
                    fraction, AnimationUtils.DECELERATE_INTERPOLATOR
            )
        }
    }

    @SuppressLint("RestrictedApi")
    private fun lerp(
            startValue: Float, endValue: Float, fraction: Float,
            interpolator: TimeInterpolator
    ): Float {
        var f = fraction
        f = interpolator.getInterpolation(f)
        return AnimationUtils.lerp(startValue, endValue, f)
    }

    override fun onSaveInstanceState(parent: CoordinatorLayout, child: ImageView): Parcelable {
        val superState = super.onSaveInstanceState(parent, child)
        val state = SavedState(superState!!)
        state.fraction = this.fraction
        return state
    }

    override fun onRestoreInstanceState(
            parent: CoordinatorLayout, child: ImageView,
            state: Parcelable
    ) {
        if (state is SavedState) {
            fraction = state.fraction
        } else {
            super.onRestoreInstanceState(parent, child, state)
        }
    }

    private class SavedState : androidx.customview.view.AbsSavedState {

        var fraction = 0f

        private constructor(parcel: Parcel) : super(parcel) {
            fraction = parcel.readFloat()
        }

        constructor(parcelable: Parcelable) : super(parcelable)

        override fun writeToParcel(dest: Parcel, flags: Int) {
            super.writeToParcel(dest, flags)
            dest.writeFloat(fraction)
        }

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(parcel: Parcel): SavedState = SavedState(parcel)

            override fun newArray(size: Int): Array<SavedState?> = arrayOfNulls(size)
        }
    }
}
