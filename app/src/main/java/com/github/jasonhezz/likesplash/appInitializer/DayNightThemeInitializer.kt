package com.github.jasonhezz.likesplash.appInitializer

import android.app.Application
import android.support.v7.app.AppCompatDelegate

class DayNightThemeInitializer : AppInitializer {
    override fun init(application: Application) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}