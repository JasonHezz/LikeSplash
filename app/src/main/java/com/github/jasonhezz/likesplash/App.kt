package com.github.jasonhezz.likesplash

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate
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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    companion object {
        private lateinit var instance: App

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}