package com.github.jasonhezz.likesplash

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/10/25.
 */
class App : Application() {
  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}