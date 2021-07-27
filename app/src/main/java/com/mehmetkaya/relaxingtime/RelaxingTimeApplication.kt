package com.mehmetkaya.relaxingtime

import android.app.Application
import com.mehmetkaya.relaxingtime.core.appinitializers.AppInitializers
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
open class RelaxingTimeApplication : Application() {
    @Inject
    lateinit var appInitializers: AppInitializers

    override fun onCreate() {
        super.onCreate()
        appInitializers.init(this)
    }
}
