package com.bonsai.pantryghost.data.usda

import com.bonsai.pantryghost.model.Food

interface UsdaRepository {
    suspend fun searchFoods(query: String): List<Food>
    suspend fun searchFoodsByBarcode(barcode: String): Food?
}