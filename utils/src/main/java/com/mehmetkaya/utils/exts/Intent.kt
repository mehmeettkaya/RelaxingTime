package com.mehmetkaya.utils.exts

import android.content.Intent

fun Intent.withNoHistory() = apply {
    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
}

fun Intent.withNoAnimation() = apply {
    addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
}
