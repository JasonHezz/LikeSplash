package com.github.jasonhezz.likesplash.util.extension

/**
 * Created by JavaCoder on 2017/10/31.
 */
enum class State { EXPANDED, COLLAPSED, IDLE }

abstract class AppBarStateChangeListener : com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener {

    private var state = State.IDLE
    private var fraction = 0f
    override fun onOffsetChanged(appBarLayout: com.google.android.material.appbar.AppBarLayout, i: Int) {
        when {
            i == 0 -> {
                if (state != State.EXPANDED) {
                    onStateChanged(
                            appBarLayout,
                            State.EXPANDED
                    )
                }
                state = State.EXPANDED
            }
            Math.abs(i) >= appBarLayout.totalScrollRange -> {
                if (state != State.COLLAPSED) {
                    onStateChanged(
                            appBarLayout,
                            State.COLLAPSED
                    )
                }
                state = State.COLLAPSED
            }
            else -> {
                if (state != State.IDLE) {
                    onStateChanged(appBarLayout, State.IDLE)
                }
                state = State.IDLE
            }
        }
        val f = Math.abs(i / appBarLayout.totalScrollRange.toFloat())
        if (f != fraction) {
            fraction = f
            onOffsetChanged(state, fraction)
        }
    }

    abstract fun onStateChanged(appBarLayout: com.google.android.material.appbar.AppBarLayout, state: State)

    abstract fun onOffsetChanged(state: State, fraction: Float)
}

