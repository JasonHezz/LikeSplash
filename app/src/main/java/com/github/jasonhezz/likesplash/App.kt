package com.github.jasonhezz.likesplash

import android.app.Application
import com.github.jasonhezz.likesplash.appInitializer.AppInitializers
import com.github.jasonhezz.likesplash.inject.appModule
import com.github.jasonhezz.likesplash.inject.controllerModule
import com.github.jasonhezz.likesplash.inject.dataModule
import com.github.jasonhezz.likesplash.inject.netModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.log.EmptyLogger


/**
 * Created by JavaCoder on 2017/10/25.
 */
class App : Application() {

    private val initializers: AppInitializers by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, controllerModule, dataModule, netModule), logger = EmptyLogger())
        initializers.init(this)
    }
}