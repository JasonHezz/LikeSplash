package com.github.jasonhezz.likesplash.util.adapter

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by JavaCoder on 2017/6/16.
 */
class TabFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragments: MutableList<Fragment> = mutableListOf()
    private val titles: MutableList<CharSequence> = mutableListOf()

    fun addFragment(fm: Fragment) {
        fragments.add(fm)
    }

    fun addFragment(fm: Fragment, title: CharSequence) {
        fragments.add(fm)
        titles.add(title)
    }

    fun addFragment(position: Int, fm: Fragment) {
        fragments.add(position, fm)
    }

    fun removeFragment(fm: Fragment) {
        fragments.remove(fm)
    }

    fun removeFragment(position: Int) {
        fragments.removeAt(position)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

    fun setPageTitle(tabLayout: TabLayout, position: Int, title: CharSequence) {

        setPageTitle(position, title)
        if (position < tabLayout.tabCount) {
            tabLayout.getTabAt(position)?.text = title
        }
    }

    private fun setPageTitle(position: Int, title: CharSequence) {
        titles[position] = title
    }
}