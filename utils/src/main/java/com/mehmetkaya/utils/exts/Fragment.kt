package com.mehmetkaya.utils.exts

import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    requireActivity().hideKeyboard()
}
