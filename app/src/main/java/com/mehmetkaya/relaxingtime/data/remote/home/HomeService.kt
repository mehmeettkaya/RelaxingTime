package com.mehmetkaya.relaxingtime.data.remote.home

import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {

    @GET("$PATH_HOME/{id}")
    suspend fun getHomeData(@Path("id") id: String): HomeData

    companion object {
        private const val PATH_HOME = "api/jsonBlob"
    }
}
