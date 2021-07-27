package com.mehmetkaya.utils.exts

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.scrollToTop(smooth: Boolean = false) {
    if (smooth) {
        smoothScrollToPosition(0)
    } else {
        scrollToPosition(0)
    }
}
