package com.mehmetkaya.relaxingtime.main.media

import com.google.android.exoplayer2.SimpleExoPlayer
import com.mehmetkaya.relaxingtime.domain.media.CreateMediaSource
import com.mehmetkaya.relaxingtime.ui.main.media.MediaDetailViewModel
import com.mehmetkaya.relaxingtime.utils.CoroutineTestRule
import com.mehmetkaya.relaxingtime.utils.ext.testIn
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MediaDetailViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var exoPlayer: SimpleExoPlayer

    @RelaxedMockK
    private lateinit var createMediaSource: CreateMediaSource

    private lateinit var mediaDetailViewModel: MediaDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { createMediaSource(any()) } returns mockk()
        mediaDetailViewModel = MediaDetailViewModel(exoPlayer, createMediaSource)

        verify { exoPlayer.addMediaSource(any()) }
        verify { exoPlayer.prepare() }
    }

    @Test
    fun `Play media when media button is clicked`() {
        coroutineTestRule.runBlockingTest {
            val state = mediaDetailViewModel.stateFlow.value
            val isPlayingMedia = state.uiState.isPlayingMedia
            val stateCollector = mediaDetailViewModel.stateFlow.testIn(this)

            mediaDetailViewModel.onMediaButtonClicked()

            verify { exoPlayer.playWhenReady = isPlayingMedia.not() }
            stateCollector.verify {
                val uiState = state.uiState.copy(isPlayingMedia.not())
                val expectedState = state.copy(uiState = uiState)
                containsItem(expectedState)
            }
        }
    }
}
