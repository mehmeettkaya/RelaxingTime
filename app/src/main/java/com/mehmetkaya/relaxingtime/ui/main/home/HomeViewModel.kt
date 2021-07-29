package com.mehmetkaya.relaxingtime.ui.main.home

import com.mehmetkaya.core.Event
import com.mehmetkaya.core.StatefulViewModel
import com.mehmetkaya.core.UiState
import com.mehmetkaya.core.launch
import com.mehmetkaya.relaxingtime.data.remote.home.Meditation
import com.mehmetkaya.relaxingtime.data.remote.home.Story
import com.mehmetkaya.relaxingtime.domain.home.FetchHome
import com.mehmetkaya.relaxingtime.domain.media.MediaDetail
import com.mehmetkaya.relaxingtime.ui.main.home.HomeViewModel.HomeEvent
import com.mehmetkaya.relaxingtime.ui.main.home.HomeViewModel.HomeEvent.NavigateToMediaDetail
import com.mehmetkaya.relaxingtime.ui.main.home.HomeViewModel.HomeState
import com.mehmetkaya.relaxingtime.ui.main.home.epoxy.BannerEpoxyItem
import com.mehmetkaya.relaxingtime.ui.main.home.epoxy.MeditationEpoxyItem
import com.mehmetkaya.relaxingtime.ui.main.home.epoxy.StoryEpoxyItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchHome: FetchHome
) : StatefulViewModel<HomeState, HomeEvent>(HomeState()) {

    init {
        launch {
            val homeState = fetchHome(ID)

            setState { homeState }
        }
    }

    fun onMeditationClicked(meditation: Meditation) = with(meditation) {
        val mediaDetail = MediaDetail(title, content, image.large, releaseDate)

        pushEvent(NavigateToMediaDetail(mediaDetail))
    }

    fun onStoryClicked(story: Story) = with(story) {
        val mediaDetail = MediaDetail(name, text, image.large, date)

        pushEvent(NavigateToMediaDetail(mediaDetail))
    }

    data class HomeState(
        val banner: BannerEpoxyItem? = null,
        val meditations: List<MeditationEpoxyItem> = emptyList(),
        val stories: List<StoryEpoxyItem> = emptyList()
    ) : UiState

    sealed class HomeEvent : Event {
        class NavigateToMediaDetail(val mediaDetail: MediaDetail) : HomeEvent()
    }

    companion object {
        private const val ID = "a07152f5-775c-11eb-a533-c90b9d55001f"
    }
}
