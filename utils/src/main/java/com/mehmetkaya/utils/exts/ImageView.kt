package com.mehmetkaya.utils.exts

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

inline fun ImageView.load(url: String?, crossinline builder: RequestBuilder<*>.() -> Unit = { }) {
    Glide.with(context)
        .load(url)
        .apply(builder)
        .into(this)
}
