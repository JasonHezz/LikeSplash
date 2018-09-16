package com.github.jasonhezz.likesplash.testutils

import androidx.test.espresso.matcher.BoundedMatcher
import androidx.viewpager.widget.ViewPager
import android.view.View
import org.hamcrest.Description

class ViewPagerMatcher(private val index: Int) : BoundedMatcher<View, androidx.viewpager.widget.ViewPager>(androidx.viewpager.widget.ViewPager::class.java) {
    override fun describeTo(desc: Description) {
        desc.appendText("with current page index = $index")
    }

    override fun matchesSafely(view: androidx.viewpager.widget.ViewPager?): Boolean = view?.currentItem == index
}