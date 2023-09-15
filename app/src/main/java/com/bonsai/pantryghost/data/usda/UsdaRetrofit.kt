package com.bonsai.pantryghost.data.usda

import com.bonsai.pantryghost.BuildConfig
import com.bonsai.pantryghost.model.Food

class UsdaRetrofit(
    private val usdaDao: UsdaDao
) : UsdaRepository {

    private val apiKey by lazy { BuildConfig.USDA_API_KEY }

    override suspend fun searchFood(query: String): List<Food> {
        val usdaSearchResponse = usdaDao.searchFoods(query, apiKey)
        val foods = usdaSearchResponse.foods.map { food ->
            val foodName = food.description
            val protein = food.getNutrientValue("Protein")
            val carbs = food.getNutrientValue("Carbohydrate, by difference")
            val fat = food.getNutrientValue("Total lipid (fat)")
            val fiber = food.getNutrientValue("Fiber, total dietary")
            val calories = food.getNutrientValue("Energy")
            Food(
                id = 0,
                name = foodName ?: "Unknown",
                calories = calories?.toFloat() ?: 0f,
                protein = protein?.toFloat() ?: 0f,
                carbs = carbs?.toFloat() ?: 0f,
                fat = fat?.toFloat() ?: 0f,
                fiber = fiber?.toFloat() ?: 0f,
            )
        }
        return foods
    }
}

private fun UsdaFood.getNutrientValue(nutrientName: String): Double? {
    if (this.servingSizeUnit != "GRM") return null
    val servingSize = this.servingSize ?: return null
    val nutrient = this.foodNutrients
        .firstOrNull { nutrient -> nutrient.nutrientName == nutrientName }?.value ?: return null
    return nutrient / servingSize
}