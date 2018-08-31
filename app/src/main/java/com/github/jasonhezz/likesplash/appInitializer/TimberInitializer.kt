package com.github.jasonhezz.likesplash.appInitializer

import android.app.Application
import com.github.jasonhezz.likesplash.BuildConfig
import timber.log.Timber

class TimberInitializer : AppInitializer {
    override fun init(application: Application) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}