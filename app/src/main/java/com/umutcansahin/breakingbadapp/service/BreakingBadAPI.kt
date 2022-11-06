package com.umutcansahin.breakingbadapp.service

import com.umutcansahin.breakingbadapp.model.character.NetworkResponse
import com.umutcansahin.breakingbadapp.model.quote.NetworkResponseDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingBadAPI {

    @GET(value = "characters?limit=16")
    suspend fun getBreakingBadList() : NetworkResponse

    @GET("quote")
    suspend fun getDetail(
        @Query("author") name: String
    ): NetworkResponseDetail
}