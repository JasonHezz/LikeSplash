package com.github.jasonhezz.likesplash.testutils;

import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

import org.hamcrest.Matcher;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;

public class TabLayoutActions {
    /**
     * Wires <code>TabLayout</code> to <code>ViewPager</code> content.
     */
    public static ViewAction setupWithViewPager(final @Nullable ViewPager viewPager) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayingAtLeast(90);
            }

            @Override
            public String getDescription() {
                return "Setup with ViewPager content";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();

                TabLayout tabLayout = (TabLayout) view;
                tabLayout.setupWithViewPager(viewPager);

                uiController.loopMainThreadUntilIdle();
            }
        };
    }

    /**
     * Wires <code>TabLayout</code> to <code>ViewPager</code> content.
     */
    public static ViewAction setupWithViewPager(
            final @Nullable ViewPager viewPager, final boolean autoRefresh) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayingAtLeast(90);
            }

            @Override
            public String getDescription() {
                return "Setup with ViewPager content";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();

                TabLayout tabLayout = (TabLayout) view;
                tabLayout.setupWithViewPager(viewPager, autoRefresh);

                uiController.loopMainThreadUntilIdle();
            }
        };
    }

    /**
     * Selects the specified tab in the <code>TabLayout</code>.
     */
    public static ViewAction selectTab(final int tabIndex) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayingAtLeast(90);
            }

            @Override
            public String getDescription() {
                return "Selects tab";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();

                TabLayout tabLayout = (TabLayout) view;
                tabLayout.getTabAt(tabIndex).select();

                uiController.loopMainThreadUntilIdle();
            }
        };
    }

    /**
     * Sets the specified tab mode in the <code>TabLayout</code>.
     */
    public static ViewAction setTabMode(final int tabMode) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayingAtLeast(90);
            }

            @Override
            public String getDescription() {
                return "Sets tab mode";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadUntilIdle();

                TabLayout tabLayout = (TabLayout) view;
                tabLayout.setTabMode(tabMode);

                uiController.loopMainThreadUntilIdle();
            }
        };
    }

    /**
     * Calls <code>setScrollPosition(position, positionOffset, true)</code> on the <code>TabLayout
     * </code>
     */
    public static ViewAction setScrollPosition(final int position, final float positionOffset) {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(TabLayout.class);
            }

            @Override
            public String getDescription() {
                return "setScrollPosition(" + position + ", " + positionOffset + ", true)";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TabLayout tabs = (TabLayout) view;
                tabs.setScrollPosition(position, positionOffset, true);
                uiController.loopMainThreadUntilIdle();
            }
        };
    }
}