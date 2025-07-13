package com.example.prommonitor.network

import okhttp3.ResponseBody
import okio.BufferedSource
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PrometheusApi {
    @GET
    suspend fun queryRange(
        @Url url: String,
        @Query("query") query: String,
        @Query("start") start: String,
        @Query("end") end: String,
        @Query("step") step: String
    ): retrofit2.Response<ResponseBody>

    @GET
    suspend fun federate(
        @Url url: String,
        @Query("match[]") match: String
    ): retrofit2.Response<BufferedSource>
}
