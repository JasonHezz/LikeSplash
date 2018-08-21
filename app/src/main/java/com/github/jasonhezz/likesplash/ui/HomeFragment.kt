package com.github.jasonhezz.likesplash.ui

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.editorial.EditorialFragment
import com.github.jasonhezz.likesplash.ui.trending.TrendingFragment
import com.github.jasonhezz.likesplash.util.adapter.TabFragmentAdapter
import kotlinx.android.synthetic.main.fragment_home.*

typealias V4Pair<F, S> = android.support.v4.util.Pair<F, S>

/**
 * Created by JasonHezz on 2017/10/15.
 */
class HomeFragment : Fragment() {

    private lateinit var tabAdapter: TabFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViewPager()
    }

    private fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_search)
        toolbar.setNavigationOnClickListener { if (activity is MainActivity) (activity as MainActivity).openDrawer() }
    }

    private fun initViewPager() {
        tabAdapter = TabFragmentAdapter(childFragmentManager)
        tabAdapter.addFragment(EditorialFragment.newInstance())
        tabAdapter.addFragment(TrendingFragment.newInstance())
        tabAdapter.addFragment(BlankFragment.newInstance())
        view_pager.apply {
            adapter = tabAdapter
            offscreenPageLimit = 3
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        }
        tab_layout?.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}