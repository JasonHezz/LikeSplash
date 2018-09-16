package com.github.jasonhezz.likesplash.testutils

import androidx.test.espresso.matcher.BoundedMatcher
import androidx.appcompat.widget.Toolbar
import android.view.View
import org.hamcrest.Description

class ToolbarTitleMatcher(private val charSequenceMatcher: CharSequence) : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
    override fun describeTo(description: Description?) {
        description?.appendText("with text: $charSequenceMatcher")
    }

    override fun matchesSafely(item: Toolbar?): Boolean = charSequenceMatcher == item?.title
}