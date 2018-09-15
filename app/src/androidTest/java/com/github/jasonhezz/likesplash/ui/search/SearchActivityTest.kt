package com.github.jasonhezz.likesplash.ui.search

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.github.jasonhezz.likesplash.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {
    @Rule
    @JvmField
    var rule: ActivityTestRule<SearchActivity> = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun testAutoComplete() {
        onView(withId(R.id.search_src_text))
                .perform(typeText("love"))
    }
}