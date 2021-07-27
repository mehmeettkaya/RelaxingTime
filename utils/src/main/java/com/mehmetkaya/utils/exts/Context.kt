package com.mehmetkaya.utils.exts

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> Context.startActivity(
    block: Intent.() -> Unit = {}
) {
    val intent = Intent(this, T::class.java).apply(block)
    startActivity(intent)
}
