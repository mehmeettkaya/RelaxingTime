package com.mehmetkaya.relaxingtime.ui.main.media

import android.os.Bundle
import android.view.View
import com.mehmetkaya.relaxingtime.R
import com.mehmetkaya.relaxingtime.core.base.BaseFragment
import com.mehmetkaya.relaxingtime.core.base.contentViewBinding
import com.mehmetkaya.relaxingtime.databinding.FragmentMediaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_media

    private val binding by contentViewBinding(FragmentMediaBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
