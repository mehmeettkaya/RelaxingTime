package com.mehmetkaya.relaxingtime.core.base

import android.view.View
import androidx.viewbinding.ViewBinding
import com.mehmetkaya.utils.viewbinding.FragmentViewBindingDelegate

fun <T : ViewBinding> BaseFragment.contentViewBinding(
    viewBindingFactory: (View) -> T
) = FragmentViewBindingDelegate(this, viewBindingFactory, { contentView!! })
