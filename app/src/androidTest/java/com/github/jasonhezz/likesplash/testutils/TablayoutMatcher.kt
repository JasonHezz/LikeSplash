package com.github.jasonhezz.likesplash.testutils

import android.support.design.widget.TabLayout
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import org.hamcrest.Description

class TablayoutMatcher(private val index: Int) : BoundedMatcher<View, TabLayout>(TabLayout::class.java) {
    override fun describeTo(description: Description?) {
        description?.appendText("with current tab index = $index")
    }

    override fun matchesSafely(view: TabLayout?): Boolean = view?.selectedTabPosition == index
}