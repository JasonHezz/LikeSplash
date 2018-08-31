package com.github.jasonhezz.likesplash

import android.app.Application
import android.content.Context
import com.github.jasonhezz.likesplash.appInitializer.*
import com.github.jasonhezz.likesplash.inject.appModule
import com.github.jasonhezz.likesplash.inject.dataModule
import com.github.jasonhezz.likesplash.inject.netModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.log.EmptyLogger


/**
 * Created by JavaCoder on 2017/10/25.
 */
class App : Application() {

    val initializers : AppInitializers by inject()

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, dataModule, netModule), logger = EmptyLogger())
        initializers.init(this)
    }

    companion object {
        private lateinit var instance: App

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}