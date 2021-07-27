package com.mehmetkaya.relaxingtime.core.appinitializers

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.view.Gravity
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.Carousel.SnapHelperFactory
import com.airbnb.epoxy.EpoxyController
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import com.mehmetkaya.relaxingtime.BuildConfig
import javax.inject.Inject

class EpoxyInitializer @Inject constructor() : AppInitializer {

    override fun init(application: Application) {
        EpoxyController.setGlobalDebugLoggingEnabled(BuildConfig.DEBUG)
        // Prevent crashing if we have the same id for different items in a EpoxyController
        EpoxyController.setGlobalDuplicateFilteringDefault(true)

        val handlerThread = HandlerThread("epoxy")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        EpoxyController.defaultDiffingHandler = handler

        // Setup Carousel to use a more sane snapping behavior
        Carousel.setDefaultGlobalSnapHelperFactory(object : SnapHelperFactory() {
            override fun buildSnapHelper(context: Context): SnapHelper {
                return GravitySnapHelper(Gravity.START)
            }
        })
    }
}
