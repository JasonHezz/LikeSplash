package com.github.jasonhezz.likesplash.ui

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.util.adapter.DrawerNavViewAdapter
import com.github.jasonhezz.likesplash.util.extension.isInNightMode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navViewAdapter: DrawerNavViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navViewAdapter = DrawerNavViewAdapter(
                supportFragmentManager, R.id.nav_home,
                R.id.frame_container, savedInstanceState
        )
        navViewAdapter.setNavigationItemSelectedListener(this)
        navViewAdapter.attachTo(nav_view)
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
            R.id.nav_wallpaper -> {
                drawer_layout?.closeDrawer(GravityCompat.START)
            }
            R.id.nav_tag -> {
                drawer_layout?.closeDrawer(GravityCompat.START)
            }
            R.id.nav_day_night -> {
                if (isInNightMode()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    recreate()
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    recreate()
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
