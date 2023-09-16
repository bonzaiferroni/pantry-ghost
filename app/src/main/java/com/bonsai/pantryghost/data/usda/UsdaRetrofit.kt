package com.bonsai.pantryghost.data.usda

import com.bonsai.pantryghost.BuildConfig
import com.bonsai.pantryghost.model.Food

class UsdaRetrofit(
    private val usdaDao: UsdaDao
) : UsdaRepository {

    private val apiKey by lazy { BuildConfig.USDA_API_KEY }

    override suspend fun searchFoods(query: String): List<Food> {
        val usdaSearchResponse = usdaDao.searchFoods(query, apiKey)
        return usdaSearchResponse.foods.map { it.toFood() }
    }

    override suspend fun searchFoodsByBarcode(barcode: String): Food? {
        val usdaSearchResponse = usdaDao.searchFoods(barcode, apiKey)
        return usdaSearchResponse.foods.firstOrNull()?.toFood()
    }
}

private fun UsdaFood.getNutrientValue(nutrientName: String): Float? {
    if (this.servingSizeUnit != "GRM") return null
    val servingSize = this.servingSize ?: return null
    val nutrient = this.foodNutrients
        .firstOrNull { nutrient -> nutrient.nutrientName == nutrientName } ?: return null
    if (nutrient.derivationCode != "LCCS") return null
    val nutrientValue = nutrient.value ?: return null
    return ((nutrientValue / (100 / servingSize)) / servingSize).toFloat()
}

private fun UsdaFood.toFood(): Food {
    return Food(
        id = 0,
        name = this.description ?: "Unknown",
        barcode = this.gtinUpc,
        calories = this.getNutrientValue("Energy") ?: 0f,
        protein = this.getNutrientValue("Protein") ?: 0f,
        carbs = this.getNutrientValue("Carbohydrate, by difference") ?: 0f,
        fat = this.getNutrientValue("Total lipid (fat)") ?: 0f,
        fiber = this.getNutrientValue("Fiber, total dietary") ?: 0f,
    )
}