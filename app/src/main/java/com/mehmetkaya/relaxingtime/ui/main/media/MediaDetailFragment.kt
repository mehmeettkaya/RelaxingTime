package com.mehmetkaya.relaxingtime.ui.main.media

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.mehmetkaya.core.withError
import com.mehmetkaya.core.withProgress
import com.mehmetkaya.core.withUiState
import com.mehmetkaya.relaxingtime.R
import com.mehmetkaya.relaxingtime.core.base.BaseFragment
import com.mehmetkaya.relaxingtime.core.base.contentViewBinding
import com.mehmetkaya.relaxingtime.databinding.FragmentMediaDetailBinding
import com.mehmetkaya.relaxingtime.domain.media.MediaDetail
import com.mehmetkaya.utils.exts.load
import com.mehmetkaya.utils.exts.toDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaDetailFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_media_detail

    private val binding by contentViewBinding(FragmentMediaDetailBinding::bind)

    private val viewModel: MediaDetailViewModel by viewModels()

    private val args: MediaDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(args.mediaDetail)
        observeViewModel()
    }

    private fun initView(mediaDetail: MediaDetail) = with(binding) {
        posterImageView.load(mediaDetail.image) { transform(CenterCrop()) }
        titleTextView.text = mediaDetail.title
        contentTextView.text = mediaDetail.content
        dateTextView.text = mediaDetail.date.toDate()
        mediaButtonImageView.setOnClickListener { viewModel.onMediaButtonClicked() }
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun observeViewModel() = with(viewModel) {
        withProgress(this, ::onProgress)
        withError(this, ::onError)
        withUiState(this) { state ->
            val imageResId = if (state.isPlayingMedia) R.drawable.ic_pause else R.drawable.ic_play
            binding.mediaButtonImageView.setImageResource(imageResId)
        }
    }
}
