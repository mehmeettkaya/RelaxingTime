package com.mehmetkaya.relaxingtime.domain.home

import com.mehmetkaya.core.domain.UseCase
import com.mehmetkaya.core.domain.mapWith
import com.mehmetkaya.relaxingtime.data.remote.home.HomeService
import com.mehmetkaya.relaxingtime.ui.main.home.HomeViewModel.HomeState
import javax.inject.Inject

class FetchHome @Inject constructor(
    private val homeService: HomeService,
    private val homeStateMapper: HomeStateMapper
) : UseCase<String, HomeState> {

    override suspend fun invoke(input: String): HomeState {
        return homeService.getHomeData(input).mapWith(homeStateMapper)
    }
}
