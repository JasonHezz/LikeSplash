package com.github.jasonhezz.likesplash.testutils

import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View

import org.hamcrest.Matcher

import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast

object ViewPagerActions {
    /**
     * Moves `ViewPager` to the right by one page.
     */
    fun scrollRight(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isDisplayingAtLeast(90)
            }

            override fun getDescription(): String {
                return "ViewPager scroll one page to the right"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()

                val viewPager = view as ViewPager
                val current = viewPager.currentItem
                viewPager.setCurrentItem(current + 1, false)

                uiController.loopMainThreadUntilIdle()
            }
        }
    }

    /**
     * Moves `ViewPager` to the left by one page.
     */
    fun scrollLeft(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isDisplayingAtLeast(90)
            }

            override fun getDescription(): String {
                return "ViewPager scroll one page to the left"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()

                val viewPager = view as ViewPager
                val current = viewPager.currentItem
                viewPager.setCurrentItem(current - 1, false)

                uiController.loopMainThreadUntilIdle()
            }
        }
    }

    /**
     * Moves `ViewPager` to the last page.
     */
    fun scrollToLast(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isDisplayingAtLeast(90)
            }

            override fun getDescription(): String {
                return "ViewPager scroll to last page"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()

                val viewPager = view as ViewPager
                val size = viewPager.adapter!!.count
                if (size > 0) {
                    viewPager.setCurrentItem(size - 1, false)
                }

                uiController.loopMainThreadUntilIdle()
            }
        }
    }

    /**
     * Moves `ViewPager` to the first page.
     */
    fun scrollToFirst(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isDisplayingAtLeast(90)
            }

            override fun getDescription(): String {
                return "ViewPager scroll to first page"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()

                val viewPager = view as ViewPager
                val size = viewPager.adapter!!.count
                if (size > 0) {
                    viewPager.setCurrentItem(0, false)
                }

                uiController.loopMainThreadUntilIdle()
            }
        }
    }

    /**
     * Moves `ViewPager` to specific page.
     */
    fun scrollToPage(page: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isDisplayingAtLeast(90)
            }

            override fun getDescription(): String {
                return "ViewPager move to a specific page"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()

                val viewPager = view as ViewPager
                viewPager.setCurrentItem(page, false)

                uiController.loopMainThreadUntilIdle()
            }
        }
    }

    /**
     * Sets the specified adapter on `ViewPager`.
     */
    fun setAdapter(adapter: PagerAdapter?): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(ViewPager::class.java)
            }

            override fun getDescription(): String {
                return "ViewPager set adapter"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()

                val viewPager = view as ViewPager
                viewPager.adapter = adapter

                uiController.loopMainThreadUntilIdle()
            }
        }
    }
}