package com.mehmetkaya.relaxingtime.main.home

import com.mehmetkaya.relaxingtime.data.remote.home.Image
import com.mehmetkaya.relaxingtime.data.remote.home.Meditation
import com.mehmetkaya.relaxingtime.data.remote.home.Story
import com.mehmetkaya.relaxingtime.domain.home.FetchHome
import com.mehmetkaya.relaxingtime.domain.media.MediaDetail
import com.mehmetkaya.relaxingtime.ui.main.home.HomeViewModel
import com.mehmetkaya.relaxingtime.ui.main.home.HomeViewModel.HomeEvent.NavigateToMediaDetail
import com.mehmetkaya.relaxingtime.ui.main.home.HomeViewModel.HomeState
import com.mehmetkaya.relaxingtime.utils.CoroutineTestRule
import com.mehmetkaya.relaxingtime.utils.ext.testIn
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @RelaxedMockK
    private lateinit var fetchHome: FetchHome

    private lateinit var homeViewModel: HomeViewModel

    private val homeState = HomeState()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { fetchHome(any()) } returns homeState
        homeViewModel = HomeViewModel(fetchHome)

        coroutineTestRule.runBlockingTest {
            val state = homeViewModel.stateFlow.value
            val stateCollector = homeViewModel.stateFlow.testIn(this)

            stateCollector.verify {
                val expectedState = state.copy(uiState = homeState)
                containsItem(expectedState)
            }
        }
    }

    @Test
    fun `Navigate to media detail when meditation item is clicked`() {
        coroutineTestRule.runBlockingTest {
            val meditation = Meditation(
                title = "title",
                subtitle = "subtitle",
                image = Image(small = "small", large = "large"),
                releaseDate = 123456,
                content = "content"
            )
            val eventCollector = homeViewModel.eventFlow.testIn(this)

            homeViewModel.onMeditationClicked(meditation)

            eventCollector.verify {
                val mediaDetail = MediaDetail(
                    meditation.title,
                    meditation.content,
                    meditation.image.large,
                    meditation.releaseDate
                )
                assertThat(expectItem()).usingRecursiveComparison()
                    .isEqualTo(NavigateToMediaDetail(mediaDetail))
            }
        }
    }

    @Test
    fun `Navigate to media detail when story item is clicked`() {
        coroutineTestRule.runBlockingTest {
            val story = Story(
                name = "name",
                category = "category",
                image = Image(small = "small", large = "large"),
                date = 123456,
                text = "text"
            )
            val eventCollector = homeViewModel.eventFlow.testIn(this)

            homeViewModel.onStoryClicked(story)

            eventCollector.verify {
                val mediaDetail = MediaDetail(
                    story.name,
                    story.text,
                    story.image.large,
                    story.date
                )
                assertThat(expectItem()).usingRecursiveComparison()
                    .isEqualTo(NavigateToMediaDetail(mediaDetail))
            }
        }
    }
}
