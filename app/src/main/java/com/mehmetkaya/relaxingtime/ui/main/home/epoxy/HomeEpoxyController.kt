package com.mehmetkaya.relaxingtime.ui.main.home.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import com.mehmetkaya.relaxingtime.R
import com.mehmetkaya.relaxingtime.ui.main.home.HomeViewModel.HomeState
import com.mehmetkaya.utils.exts.carouselPadding
import com.mehmetkaya.utils.exts.scrollToTop
import com.mehmetkaya.widgets.nonLeakyCarousel

class HomeEpoxyController(
    private val homeCallbacks: HomeCallbacks
) : TypedEpoxyController<HomeState>() {

    interface HomeCallbacks {
        fun onItemClicked(id: String)
    }

    override fun buildModels(data: HomeState) = with(data) {
        buildMeditationModels(meditations)
        buildBannerModel(banner)
        buildStoryModels(stories)
    }

    private fun buildMeditationModels(items: List<MeditationEpoxyItem>) {
        if (items.isEmpty()) return

        val headerEpoxyItem = HeaderEpoxyItem(R.string.meditations_title)
        buildHeaderModel(headerEpoxyItem)

        nonLeakyCarousel {
            id("meditation_carousel")
            padding(
                carouselPadding(
                    horizontalRes = R.dimen.spacing_none,
                    verticalRes = R.dimen.spacing_none,
                    itemSpacingRes = R.dimen.spacing_big
                )
            )
            onBind { _, carousel, _ -> carousel.scrollToTop() }
            models(items.map(this@HomeEpoxyController::buildMeditationModel))
            spanSizeOverride { _, _, _ -> SPAN_SIZE_FULL_WIDTH }
        }
    }

    private fun buildBannerModel(item: BannerEpoxyItem?) {
        if (item == null) return

        banner {
            id(item.userName)
            item(item)
            spanSizeOverride { _, _, _ -> SPAN_SIZE_FULL_WIDTH }
        }
    }

    private fun buildStoryModels(items: List<StoryEpoxyItem>) {
        if (items.isEmpty()) return

        val headerEpoxyItem = HeaderEpoxyItem(R.string.stories_title)
        buildHeaderModel(headerEpoxyItem)

        items.forEach(::buildStoryModel)
    }

    private fun buildMeditationModel(item: MeditationEpoxyItem): MeditationEpoxyModel_ {
        return MeditationEpoxyModel_()
            .id(item.meditation.title)
            .item(item)
    }

    private fun buildHeaderModel(item: HeaderEpoxyItem) {
        header {
            id(item.titleRes)
            item(item)
            spanSizeOverride { _, _, _ -> SPAN_SIZE_FULL_WIDTH }
        }
    }

    private fun buildStoryModel(item: StoryEpoxyItem) {
        story {
            id(item.story.name)
            item(item)
            spanSizeOverride { _, _, _ -> SPAN_SIZE_STORY }
        }
    }

    companion object {
        const val SPAN_SIZE_STORY = 1
        const val SPAN_SIZE_FULL_WIDTH = 2
    }
}
