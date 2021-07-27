package com.mehmetkaya.utils.viewbinding

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.mehmetkaya.utils.exts.unsafeLazy

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T
) = FragmentViewBindingDelegate(this, viewBindingFactory)

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline viewBindingFactory: (LayoutInflater) -> T
) = unsafeLazy {
    viewBindingFactory(layoutInflater)
}

inline fun <T : ViewBinding> View.viewBinding(
    crossinline viewBindingFactory: (View) -> T
) = unsafeLazy {
    viewBindingFactory(this)
}
