package com.mehmetkaya.relaxingtime.ui.main.media

import com.mehmetkaya.core.NoEvent
import com.mehmetkaya.core.StatefulViewModel
import com.mehmetkaya.core.UiState
import com.mehmetkaya.relaxingtime.ui.main.media.MediaDetailViewModel.MediaDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaDetailViewModel @Inject constructor(

) : StatefulViewModel<MediaDetailState, NoEvent>(MediaDetailState()) {

    fun onMediaButtonClicked() {
        val isPlayingMedia = currentUiState.isPlayingMedia.not()

        setState { copy(isPlayingMedia = isPlayingMedia) }
    }

    data class MediaDetailState(
        val isPlayingMedia: Boolean = false
    ) : UiState
}
