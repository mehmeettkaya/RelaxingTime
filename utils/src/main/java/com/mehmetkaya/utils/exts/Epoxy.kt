package com.mehmetkaya.utils.exts

import androidx.annotation.DimenRes
import com.airbnb.epoxy.Carousel

fun carouselPadding(
    @DimenRes horizontalRes: Int,
    @DimenRes verticalRes: Int,
    @DimenRes itemSpacingRes: Int
): Carousel.Padding {
    return Carousel.Padding.resource(
        horizontalRes,
        verticalRes,
        horizontalRes,
        verticalRes,
        itemSpacingRes
    )
}
