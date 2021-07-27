package com.mehmetkaya.utils.exts

import java.util.regex.Pattern

private const val MIN_TEXT_LENGTH = 2
private const val PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$"

fun String.isValidUserName(): Boolean {
    return this.length > MIN_TEXT_LENGTH
}

fun String.isValidPassword(): Boolean {
    val pattern = Pattern.compile(PASSWORD_REGEX)

    return pattern.matcher(this).matches()
}
