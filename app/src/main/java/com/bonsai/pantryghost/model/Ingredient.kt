package com.bonsai.pantryghost.model

data class Ingredient(
    val id: Int,
    val foodId: Int,
    val mealId: Int,
    val grams: Float,
)