package com.mehmetkaya.relaxingtime.ui.main.media

import com.google.android.exoplayer2.SimpleExoPlayer
import com.mehmetkaya.core.NoEvent
import com.mehmetkaya.core.StatefulViewModel
import com.mehmetkaya.core.UiState
import com.mehmetkaya.core.launch
import com.mehmetkaya.relaxingtime.domain.media.CreateMediaSource
import com.mehmetkaya.relaxingtime.ui.main.media.MediaDetailViewModel.MediaDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaDetailViewModel @Inject constructor(
    private val exoPlayer: SimpleExoPlayer,
    private val createMediaSource: CreateMediaSource
) : StatefulViewModel<MediaDetailState, NoEvent>(MediaDetailState()) {

    init {
        launch {
            val mediaSource = createMediaSource(MEDIA_URL)

            exoPlayer.addMediaSource(mediaSource)
            exoPlayer.prepare()
        }
    }

    fun onMediaButtonClicked() {
        val isPlayingMedia = currentUiState.isPlayingMedia.not()

        exoPlayer.playWhenReady = isPlayingMedia
        setState { copy(isPlayingMedia = isPlayingMedia) }
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.playWhenReady = false
    }

    data class MediaDetailState(
        val isPlayingMedia: Boolean = false
    ) : UiState

    companion object {
        private const val MEDIA_URL = "https://d2r0ihkco3hemf.cloudfront.net/bgxupraW2spUpr_y2.mp3"
    }
}
