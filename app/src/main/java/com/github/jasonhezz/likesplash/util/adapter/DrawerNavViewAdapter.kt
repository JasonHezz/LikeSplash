package com.github.jasonhezz.likesplash.util.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.HomeFragment
import com.github.jasonhezz.likesplash.ui.collection.CollectionFragment
import com.github.jasonhezz.likesplash.ui.explore.ExploreFragment
import com.github.jasonhezz.likesplash.ui.trending.TrendingFragment


/**
 * Created by JavaCoder on 2017/10/16.
 */
class DrawerNavViewAdapter(fm: FragmentManager, defaultMenuId: Int, containerId: Int,
    savedInstanceState: Bundle?)
  : NavigationViewAdapter(fm, defaultMenuId, containerId, savedInstanceState) {
  override fun getFragment(menuItemId: Int): Fragment = when (menuItemId) {
    R.id.nav_home -> TrendingFragment.newInstance()
    R.id.nav_collection -> CollectionFragment.newInstance()
    else -> ExploreFragment.newInstance()
  }

  override fun shouldHandleMenuItem(
      menuItemId: Int) = menuItemId != R.id.nav_day_night && menuItemId != R.id.nav_download && menuItemId != R.id.nav_setting
}
