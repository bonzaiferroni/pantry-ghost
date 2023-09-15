package com.bonsai.pantryghost.data.usda

import retrofit2.http.GET
import retrofit2.http.Query

interface UsdaDao {
    @GET("search")
    suspend fun searchFoods(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
    ): UsdaSearchResponse
}