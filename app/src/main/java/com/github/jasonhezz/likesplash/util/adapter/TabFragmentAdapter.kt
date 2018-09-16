package com.github.jasonhezz.likesplash.util.adapter

/**
 * Created by JavaCoder on 2017/6/16.
 */
class TabFragmentAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

    private val fragments: MutableList<androidx.fragment.app.Fragment> = mutableListOf()
    private val titles: MutableList<CharSequence> = mutableListOf()

    fun addFragment(fm: androidx.fragment.app.Fragment) {
        fragments.add(fm)
    }

    fun addFragment(fm: androidx.fragment.app.Fragment, title: CharSequence) {
        fragments.add(fm)
        titles.add(title)
    }

    fun addFragment(position: Int, fm: androidx.fragment.app.Fragment) {
        fragments.add(position, fm)
    }

    fun removeFragment(fm: androidx.fragment.app.Fragment) {
        fragments.remove(fm)
    }

    fun removeFragment(position: Int) {
        fragments.removeAt(position)
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

    fun setPageTitle(tabLayout: com.google.android.material.tabs.TabLayout, position: Int, title: CharSequence) {

        setPageTitle(position, title)
        if (position < tabLayout.tabCount) {
            tabLayout.getTabAt(position)?.text = title
        }
    }

    private fun setPageTitle(position: Int, title: CharSequence) {
        titles[position] = title
    }
}