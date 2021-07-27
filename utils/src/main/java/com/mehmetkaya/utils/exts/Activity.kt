package com.mehmetkaya.utils.exts

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus ?: findViewById(android.R.id.content) ?: View(this)
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
