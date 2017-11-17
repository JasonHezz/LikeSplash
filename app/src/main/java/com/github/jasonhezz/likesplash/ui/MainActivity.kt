package com.github.jasonhezz.likesplash.ui

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.util.adapter.DrawerNavViewAdapter
import com.github.jasonhezz.likesplash.util.dp2px
import com.github.jasonhezz.likesplash.util.isInNightMode
import com.github.jasonhezz.likesplash.util.scale
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

  private lateinit var navViewAdapter: DrawerNavViewAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navViewAdapter = DrawerNavViewAdapter(supportFragmentManager, R.id.nav_home,
        R.id.frame_container, savedInstanceState)
    navViewAdapter.setNavigationItemSelectedListener(this)
    navViewAdapter.attachTo(nav_view)
//     drawer_layout.drawerElevation = 0F
//     drawer_layout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
//       override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
//         super.onDrawerSlide(drawerView, slideOffset)
//         val moveFactor = nav_view.width * slideOffset
//         frame_container.translationX = moveFactor
//         frame_container.scale = 1 - slideOffset / 4
//         frame_container.cardElevation = slideOffset * dp2px(10f)
//       }
//     })
//     drawer_layout.setScrimColor(Color.TRANSPARENT)
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    outState?.let { navViewAdapter.onSaveInstanceState(it) }
  }

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.nav_home -> {
        drawer_layout?.closeDrawer(GravityCompat.START)
      }
      R.id.nav_collection -> {
        drawer_layout?.closeDrawer(GravityCompat.START)
      }
      R.id.nav_tag -> {
        drawer_layout?.closeDrawer(GravityCompat.START)
      }
      R.id.nav_day_night -> {
        if (isInNightMode()) {
          delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
          delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        drawer_layout?.closeDrawer(GravityCompat.START)
      }
    }
    return true
  }

  fun openDrawer() {
    drawer_layout?.openDrawer(Gravity.START)
  }
}
