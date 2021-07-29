package com.mehmetkaya.utils.exts

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDate(): String {
    val pattern = "MM/dd/yyyy, LLL"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())

    return simpleDateFormat.format(this * 1000L)
}
