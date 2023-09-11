package com.bonsai.pantryghost.model

data class Serving(
    val foodId: Int,
    val servingId: Int,
    val mealTimeId: Int,
    val foodName: String,
    val grams: Float,
    val calories: Float,
    val protein: Float,
    val carbs: Float,
    val fat: Float,
    val fiber: Float,
)
