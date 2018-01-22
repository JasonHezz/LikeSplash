package com.github.jasonhezz.likesplash.ui.explore

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.MainActivity
import com.github.jasonhezz.unofficialsplash.home.TabFragmentAdapter
import kotlinx.android.synthetic.main.fragment_tab_explore.*

/**
 * Created by JavaCoder on 2017/12/21.
 */
class ExploreTabFragment : Fragment() {

  private lateinit var tabAdapter: TabFragmentAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {

    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_tab_explore, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initToolbar()
    initViewPager()
  }

  private fun initToolbar() {
    toolbar.setNavigationOnClickListener { if (activity is MainActivity) (activity as MainActivity).openDrawer() }
  }

  private fun initViewPager() {
    tabAdapter = TabFragmentAdapter(childFragmentManager)
    tabAdapter.addFragment(PopularPhotoFragment.newInstance())
    tabAdapter.addFragment(PopularCollectionFragment.newInstance())
    view_pager.apply {
      adapter = tabAdapter
      offscreenPageLimit = 3
      addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
    }
    tab_layout?.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))
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
