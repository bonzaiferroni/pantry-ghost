package com.bonsai.pantryghost.data.usda

import com.bonsai.pantryghost.model.Food

fun UsdaFood.toFoodInfo(): FoodInfo {
    return FoodInfo(
        food = Food(
            id = 0,
            name = null,
            description = this.description,
            barcode = this.gtinUpc,
            servingSize = this.servingSize ?: 0f,
            calories = this.getNutrientValue("Energy") ?: 0f,
            protein = this.getNutrientValue("Protein") ?: 0f,
            carbs = this.getNutrientValue("Carbohydrate, by difference") ?: 0f,
            fat = this.getNutrientValue("Total lipid (fat)") ?: 0f,
            fiber = this.getNutrientValue("Fiber, total dietary") ?: 0f,
        ),
        category = this.foodCategory ?: "Unknown",
    )
}

fun UsdaFood.getNutrientValue(nutrientName: String): Float? {
    if (this.servingSizeUnit != "GRM") return null
    val servingSize = this.servingSize ?: return null
    val nutrient = this.foodNutrients
        .firstOrNull { nutrient -> nutrient.nutrientName == nutrientName } ?: return null
    if (nutrient.derivationCode != "LCCS") return null
    val nutrientValue = nutrient.value ?: return null
    return ((nutrientValue / (100 / servingSize)) / servingSize).toFloat()
}