package com.mehmetkaya.widgets

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT

@ModelView(saveViewState = true, autoLayout = MATCH_WIDTH_WRAP_HEIGHT)
open class NonLeakyCarousel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : Carousel(context, attrs) {
    /**
     * Sharing a single view pool in the same activity make the fragments leak since the views inside the Carousel
     * hold a reference to the fragments via callbacks. Returning false here makes Carousel prevent
     * sharing view pool, which also prevents leaking fragments.
     */
    override fun shouldShareViewPoolAcrossContext() = false
}

inline fun <T> NonLeakyCarouselModelBuilder.withModelsFrom(
    items: List<T>,
    modelBuilder: (T) -> EpoxyModel<*>
) {
    models(items.map { modelBuilder(it) })
}
