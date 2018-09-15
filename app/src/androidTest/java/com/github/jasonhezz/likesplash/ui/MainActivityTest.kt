package com.github.jasonhezz.likesplash.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.DrawerMatchers
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.Toolbar
import android.widget.ImageButton
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.testutils.*
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testContent() {
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(ToolbarTitleMatcher("Home")))
    }

    @Test
    fun testDrawer() {
        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open())
        onView(withId(R.id.drawer_layout))
                .check(matches(DrawerMatchers.isOpen()))
        onView(withId(R.id.nav_view))
                .check(matches(isDisplayed()))

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.close())
        onView(withId(R.id.drawer_layout))
                .check(matches(DrawerMatchers.isClosed()))
        onView(withId(R.id.nav_view))
                .check(matches(Matchers.not(isDisplayed())))
    }

    @Test
    fun testNavigationView() {
        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open())
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_collection))
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(ToolbarTitleMatcher("Collection")))

        onView(withId(R.id.drawer_layout))
                .perform(DrawerActions.open())
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_home))
        onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(ToolbarTitleMatcher("Home")))
    }

    @Test
    fun testHomeViewPager() {
        onView(withId(R.id.tab_layout))
                .perform(TabLayoutActions.selectTab(2))
        onView(withId(R.id.view_pager))
                .check(matches(ViewPagerMatcher(2)))
    }

    @Test
    fun testHomeTabLayout() {
        onView(withId(R.id.view_pager))
                .perform(ViewPagerActions.scrollRight())
                .perform(ViewPagerActions.scrollRight())
                .perform(ViewPagerActions.scrollRight())
        onView(withId(R.id.tab_layout))
                .check(matches(TablayoutMatcher(2)))
    }

    @Test
    fun testHomeNavigation() {
        //find toolbar navigation button
        onView(allOf(withParent(
                isAssignableFrom(Toolbar::class.java)),
                isAssignableFrom(ImageButton::class.java))).perform(click())
        onView(withId(R.id.drawer_layout))
                .check(matches(DrawerMatchers.isOpen()))
    }
}