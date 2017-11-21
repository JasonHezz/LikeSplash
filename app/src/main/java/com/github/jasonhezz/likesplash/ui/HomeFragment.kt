package com.github.jasonhezz.likesplash.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.util.isTranslucentNav
import com.github.jasonhezz.likesplash.util.isTranslucentStatus
import com.github.jasonhezz.likesplash.util.marginBottom
import com.github.jasonhezz.likesplash.util.marginTop
import com.github.jasonhezz.unofficialsplash.home.TabFragmentAdapter
import com.readystatesoftware.systembartint.SystemBarTintManager
import it.sephiroth.android.library.bottomnavigation.BottomNavigation
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.search_bar.*

typealias V4Pair<F, S> = android.support.v4.util.Pair<F, S>

/**
 * Created by JasonHezz on 2017/10/15.
 */
class HomeFragment : Fragment() {

  private lateinit var tabAdapter: TabFragmentAdapter
  private val systemBarTintManager by lazy { SystemBarTintManager(activity) }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_home, container, false)
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    tabAdapter = TabFragmentAdapter(childFragmentManager)
    tabAdapter.addFragment(ListFragment.newInstance())
    tabAdapter.addFragment(BlankFragment.newInstance())
    tabAdapter.addFragment(BlankFragment.newInstance())

    initView()
  }

  private fun initView() {

    view_pager?.apply {
      //TODO make it more readable
      if (systemBarTintManager.config.isNavigationAtBottom && context.isTranslucentNav) {
        setPadding(left, top, right, bottom + systemBarTintManager.config.navigationBarHeight)
      }
      adapter = tabAdapter
      offscreenPageLimit = 3
    }

    search_toolbar?.apply {
      if (context.isTranslucentStatus) {
        marginTop(systemBarTintManager.config.statusBarHeight)
      }
      onNavigationOnClickListener = { if (activity is MainActivity) (activity as MainActivity).openDrawer() }
      onBarClickListener = {
        activity?.let {
          val intent = Intent(context, SearchActivity::class.java)
          startActivity(intent,
              ActivityOptionsCompat.makeSceneTransitionAnimation(
                  it,
                  V4Pair<View, String>(search_toolbar,
                      ViewCompat.getTransitionName(search_toolbar)),
                  V4Pair<View, String>(search_nav,
                      ViewCompat.getTransitionName(search_toolbar)))
                  .toBundle())
        }

      }
    }
    //BottomNavigation#onSizeChanged will auto set margin -bottomInset which not needed
    bottom_nav?.apply {
      addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
        marginBottom(0)
      }
      setOnMenuItemClickListener(object : BottomNavigation.OnMenuItemSelectionListener {
        override fun onMenuItemSelect(itemId: Int, position: Int, fromUser: Boolean) {
          if (fromUser) view_pager.currentItem = position
        }

        override fun onMenuItemReselect(itemId: Int, position: Int, fromUser: Boolean) {

        }

      })
    }
  }

  companion object {

    @JvmStatic
    fun newInstance() = HomeFragment()
  }
}