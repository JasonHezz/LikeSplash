package com.github.jasonhezz.likesplash.testutils

import com.google.android.material.tabs.TabLayout
import androidx.test.espresso.matcher.BoundedMatcher
import android.view.View
import org.hamcrest.Description

class TablayoutMatcher(private val index: Int) : BoundedMatcher<View, com.google.android.material.tabs.TabLayout>(com.google.android.material.tabs.TabLayout::class.java) {
    override fun describeTo(description: Description?) {
        description?.appendText("with current tab index = $index")
    }

    override fun matchesSafely(view: com.google.android.material.tabs.TabLayout?): Boolean = view?.selectedTabPosition == index
}