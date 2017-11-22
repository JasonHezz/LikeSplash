package com.github.jasonhezz.likesplash.profile

import android.content.Context
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.ListFragment
import com.github.jasonhezz.likesplash.util.AppBarStateChangeListener
import com.github.jasonhezz.likesplash.util.State
import com.github.jasonhezz.likesplash.util.showSnackbar
import com.github.jasonhezz.unofficialsplash.home.TabFragmentAdapter
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by JavaCoder on 2017/6/28.
 */
class ProfileFragment : Fragment() {

  private lateinit var tabAdapter: TabFragmentAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_profile, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initToolbar()
    initViewPager()
  }

  private fun initToolbar() {
    user_avatar.setOnClickListener { it.showSnackbar("avatar") }
    profile_toolbar?.apply {
      setNavigationOnClickListener { activity?.supportFinishAfterTransition() }
    }
    app_bar_layout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
      override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {

      }

      override fun onOffsetChanged(state: State, fraction: Float) {
        ViewCompat.setTranslationZ(app_bar_layout, 6f * fraction)
      }
    })
  }


  private fun initViewPager() {
    tabAdapter = TabFragmentAdapter(childFragmentManager)
    tabAdapter.addFragment(ListFragment.newInstance())
    tabAdapter.addFragment(ListFragment.newInstance())
    tabAdapter.addFragment(ListFragment.newInstance())
    view_pager.apply {
      adapter = tabAdapter
      offscreenPageLimit = 3
      addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
    }
    tab_layout?.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
  }

  companion object {
    @JvmStatic
    fun newInstance() = ProfileFragment()
  }
}