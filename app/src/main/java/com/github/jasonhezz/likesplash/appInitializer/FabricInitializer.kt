package com.github.jasonhezz.likesplash.appInitializer

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.github.jasonhezz.likesplash.BuildConfig
import io.fabric.sdk.android.Fabric

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