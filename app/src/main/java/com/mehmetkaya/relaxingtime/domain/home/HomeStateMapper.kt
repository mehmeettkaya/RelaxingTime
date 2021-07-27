package com.mehmetkaya.relaxingtime.domain.home

import com.mehmetkaya.core.domain.Mapper
import com.mehmetkaya.relaxingtime.data.local.LocalStorageModule
import com.mehmetkaya.relaxingtime.data.remote.home.HomeData
import com.mehmetkaya.relaxingtime.ui.main.home.HomeViewModel.HomeState
import com.mehmetkaya.relaxingtime.ui.main.home.epoxy.BannerEpoxyItem
import com.mehmetkaya.relaxingtime.ui.main.home.epoxy.MeditationEpoxyItem
import com.mehmetkaya.relaxingtime.ui.main.home.epoxy.StoryEpoxyItem
import com.mehmetkaya.utils.preference.StringPreference
import javax.inject.Inject
import javax.inject.Named

class HomeStateMapper @Inject constructor(
    @Named(LocalStorageModule.USER_NAME_PREF) private val userNamePref: StringPreference
) : Mapper<HomeData, HomeState> {

    override fun map(input: HomeData) = with(input) {
        val banner = if (isBannerEnabled) BannerEpoxyItem(userNamePref.get()) else null
        val meditations = meditations.map(::MeditationEpoxyItem)
        val stories = stories.map(::StoryEpoxyItem)

        HomeState(banner, meditations, stories)
    }
}