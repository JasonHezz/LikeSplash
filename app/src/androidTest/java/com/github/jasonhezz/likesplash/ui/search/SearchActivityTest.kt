package com.github.jasonhezz.likesplash.ui.search

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
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