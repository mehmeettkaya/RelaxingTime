package com.mehmetkaya.relaxingtime.core.appinitializers

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class TimberInitializer @Inject constructor() : AppInitializer {

    override fun init(application: Application) {
        Timber.plant(DebugTree())
    }
}
