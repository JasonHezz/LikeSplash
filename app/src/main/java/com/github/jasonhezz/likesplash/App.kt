package com.github.jasonhezz.likesplash

import android.app.Application
import android.content.Context
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/10/25.
 */
class App : Application() {

  init {
    instance = this
  }

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  companion object {
    private var instance: App? = null

    fun applicationContext(): Context {
      return instance!!.applicationContext
    }
  }
}