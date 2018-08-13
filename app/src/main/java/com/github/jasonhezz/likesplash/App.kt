package com.github.jasonhezz.likesplash

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate
import com.github.jasonhezz.likesplash.inject.appModule
import org.koin.android.ext.android.startKoin
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
        startKoin(this, listOf(appModule))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    companion object {
        private lateinit var instance: App

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}