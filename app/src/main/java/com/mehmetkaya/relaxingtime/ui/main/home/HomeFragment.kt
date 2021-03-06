package com.mehmetkaya.relaxingtime.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyItemSpacingDecorator
import com.mehmetkaya.core.withError
import com.mehmetkaya.core.withEvent
import com.mehmetkaya.core.withProgress
import com.mehmetkaya.core.withUiState
import com.mehmetkaya.relaxingtime.R
import com.mehmetkaya.relaxingtime.core.base.BaseFragment
import com.mehmetkaya.relaxingtime.core.base.contentViewBinding
import com.mehmetkaya.relaxingtime.data.remote.home.Meditation
import com.mehmetkaya.relaxingtime.data.remote.home.Story
import com.mehmetkaya.relaxingtime.databinding.FragmentHomeBinding
import com.mehmetkaya.relaxingtime.domain.media.MediaDetail
import com.mehmetkaya.relaxingtime.ui.main.home.HomeViewModel.HomeEvent.NavigateToMediaDetail
import com.mehmetkaya.relaxingtime.ui.main.home.epoxy.HomeEpoxyController
import com.mehmetkaya.relaxingtime.ui.main.home.epoxy.HomeEpoxyController.Companion.SPAN_SIZE_FULL_WIDTH
import com.mehmetkaya.relaxingtime.ui.main.home.epoxy.HomeEpoxyController.HomeCallbacks
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_home

    private val binding by contentViewBinding(FragmentHomeBinding::bind)

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var epoxyController: HomeEpoxyController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEpoxyController()
        observeViewModel()
    }

    private fun initEpoxyController() {
        epoxyController = HomeEpoxyController(object : HomeCallbacks {
            override fun onMeditationClicked(meditation: Meditation) {
                viewModel.onMeditationClicked(meditation)
            }

            override fun onStoryClicked(story: Story) {
                viewModel.onStoryClicked(story)
            }
        }).apply { spanCount = SPAN_SIZE_FULL_WIDTH }

        val gridLayoutManager = GridLayoutManager(context, SPAN_SIZE_FULL_WIDTH).apply {
            spanSizeLookup = epoxyController.spanSizeLookup
        }

        binding.homeEpoxyRecyclerView.apply {
            layoutManager = gridLayoutManager

            val spacingDecorator = EpoxyItemSpacingDecorator(resources.getDimensionPixelSize(R.dimen.spacing_big))
            addItemDecoration(spacingDecorator)

            setController(epoxyController)
        }
    }

    private fun observeViewModel() = with(viewModel) {
        withUiState(this, epoxyController::setData)
        withProgress(this, ::onProgress)
        withError(this, ::onError)
        withEvent(this) { event ->
            when (event) {
                is NavigateToMediaDetail -> navigateToMediaDetail(event.mediaDetail)
            }
        }
    }

    private fun navigateToMediaDetail(mediaDetail: MediaDetail) {
        val action = HomeFragmentDirections.actionHomeToMediaDetail(mediaDetail)
        findNavController().navigate(action)
    }
}
