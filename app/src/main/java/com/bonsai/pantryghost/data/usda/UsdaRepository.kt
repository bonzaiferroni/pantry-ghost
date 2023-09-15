package com.bonsai.pantryghost.data.usda

import com.bonsai.pantryghost.model.Food

interface UsdaRepository {
    suspend fun searchFood(query: String): List<Food>
}