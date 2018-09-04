package com.github.jasonhezz.likesplash.appInitializer

import android.app.Application

class FabricInitializer : AppInitializer {
    override fun init(application: Application) {
        Fabric.with(application, Crashlytics.Builder().core(
                CrashlyticsCore
                        .Builder()
                        .disabled(BuildConfig.DEBUG)
                        .build()
        ).build())
    }
}