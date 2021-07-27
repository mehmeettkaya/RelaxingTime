package com.mehmetkaya.relaxingtime.domain

import com.mehmetkaya.core.domain.UseCase
import com.mehmetkaya.relaxingtime.data.remote.HomeData
import com.mehmetkaya.relaxingtime.data.remote.HomeService
import javax.inject.Inject

class FetchHome @Inject constructor(
    private val homeService: HomeService
) : UseCase<String, HomeData> {

    override suspend fun invoke(input: String): HomeData {
        return homeService.getHomeData(input)
    }
}
