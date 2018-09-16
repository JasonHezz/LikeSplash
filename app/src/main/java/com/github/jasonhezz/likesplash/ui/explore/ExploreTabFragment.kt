package com.github.jasonhezz.likesplash.ui.explore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.MainActivity
import com.github.jasonhezz.likesplash.ui.search.SearchActivity
import com.github.jasonhezz.likesplash.util.adapter.TabFragmentAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_explore_tab.*

/**
 * Created by JavaCoder on 2017/12/21.
 */
class ExploreTabFragment : androidx.fragment.app.Fragment() {

    private lateinit var tabAdapter: TabFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_explore_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViewPager()
    }

    private fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_search)
        toolbar.setNavigationOnClickListener { if (activity is MainActivity) (activity as MainActivity).openDrawer() }
        toolbar.setOnMenuItemClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
            true
        }
    }

    private fun initViewPager() {
        tabAdapter = TabFragmentAdapter(childFragmentManager)
        tabAdapter.addFragment(PopularPhotoFragment.newInstance())
        tabAdapter.addFragment(PopularCollectionFragment.newInstance())
        view_pager.apply {
            adapter = tabAdapter
            offscreenPageLimit = 3
            addOnPageChangeListener(com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        }
        tab_layout?.addOnTabSelectedListener(com.google.android.material.tabs.TabLayout.ViewPagerOnTabSelectedListener(view_pager))
    }

    companion object {
        @JvmStatic
        fun newInstance(): ExploreTabFragment {
            val fragment = ExploreTabFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
