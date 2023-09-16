package com.bonsai.pantryghost.data.usda

interface UsdaRepository {
    suspend fun searchFoods(query: String): List<FoodInfo>
    suspend fun searchFoodsByBarcode(barcode: String): FoodInfo?
}