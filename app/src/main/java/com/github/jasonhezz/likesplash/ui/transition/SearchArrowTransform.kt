package com.github.jasonhezz.likesplash.ui.transition

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.util.Property
import android.view.ViewGroup
import android.widget.ImageButton
import com.github.jasonhezz.likesplash.R

/**
 * Created by JasonHezz on 2017/9/21.
 */
class SearchArrowTransform(context: Context, attrs: AttributeSet) : Transition(
    context,
    attrs
) {

    private var initialProgress: Float = 0f
    private var finalProgress: Float = 1f
    private val background = DrawerArrowDrawable(context)

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.SearchArrowTransform)
        initialProgress = ta.getFloat(R.styleable.SearchArrowTransform_initialProgress, 0f)
        finalProgress = ta.getFloat(R.styleable.SearchArrowTransform_finalProgress, 1f)
        ta.recycle()
    }

    override fun createAnimator(
        sceneRoot: ViewGroup?, startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        if (endValues?.view is ImageButton) {
            val iv = endValues.view as ImageButton
            iv.setImageDrawable(background)
            return ObjectAnimator.ofFloat(background, PROGRESS, initialProgress, finalProgress)
        }
        return null
    }

    override fun captureStartValues(values: TransitionValues) {
        captureValues(values, initialProgress)
    }

    override fun captureEndValues(values: TransitionValues) {
        captureValues(values, finalProgress)
    }

    private fun captureValues(transitionValues: TransitionValues, value: Any) {
        if (transitionValues.view is ImageButton) {
            transitionValues.values.put(PROPNAME_DRAWABLE, value)
        }
    }

    override fun getTransitionProperties(): Array<String> = arrayOf(PROPNAME_DRAWABLE)

    companion object {

        const val PROPNAME_DRAWABLE = "splash:transform:progress"

        var PROGRESS = object : Property<DrawerArrowDrawable, Float>(Float::class.java, "progress") {
            override fun get(drawerArrowDrawable: DrawerArrowDrawable): Float =
                drawerArrowDrawable.progress

            override fun set(drawerArrowDrawable: DrawerArrowDrawable, value: Float) {
                drawerArrowDrawable.progress = value
            }
        }
    }
}