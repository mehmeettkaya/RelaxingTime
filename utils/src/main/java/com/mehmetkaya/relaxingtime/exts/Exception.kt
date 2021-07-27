package com.mehmetkaya.relaxingtime.exts

import kotlinx.serialization.SerializationException

fun Throwable.isServiceError(): Boolean = when (this) {
    is SerializationException -> true
    else -> false
}

fun Throwable.throwIfNotServiceError() {
    if (isServiceError().not()) {
        throw this
    }
}
