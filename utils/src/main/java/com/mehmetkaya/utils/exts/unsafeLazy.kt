package com.mehmetkaya.utils.exts

import kotlin.LazyThreadSafetyMode.NONE

fun <T> unsafeLazy(initializer: () -> T): Lazy<T> = lazy(NONE, initializer)
