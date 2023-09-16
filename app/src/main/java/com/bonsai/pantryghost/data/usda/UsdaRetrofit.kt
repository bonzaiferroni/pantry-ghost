package com.bonsai.pantryghost.data.usda

import com.bonsai.pantryghost.BuildConfig
import com.bonsai.pantryghost.model.Food

class UsdaRetrofit(
    private val usdaDao: UsdaDao
) : UsdaRepository {

    private val apiKey by lazy { BuildConfig.USDA_API_KEY }

    override suspend fun searchFoods(query: String): List<FoodInfo> {
        val usdaSearchResponse = usdaDao.searchFoods(query, apiKey)
        return usdaSearchResponse.foods.map { it.toFoodInfo() }
    }

    override suspend fun searchFoodsByBarcode(barcode: String): FoodInfo? {
        val usdaSearchResponse = usdaDao.searchFoods(barcode, apiKey)
        return usdaSearchResponse.foods.firstOrNull()?.toFoodInfo()
    }
}

data class FoodInfo(
    val food: Food,
    val category: String,
)