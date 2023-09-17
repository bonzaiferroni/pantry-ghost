package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.MealTime
import com.bonsai.pantryghost.model.MealType
import com.bonsai.pantryghost.model.ServingAmount
import java.time.Instant
import java.time.temporal.ChronoUnit

object SampleData {

    val foods by lazy {
        var id = 0
        listOf(
            Food(++id, "Apple", "shiny and red", "1234", 50f, 0.52f, 0.026f, 13.8f, 0.017f, 0.028f),
            Food(++id, "Chicken Breast", null, null, 60f, 1.65f, 0.31f, 0f, 0.036f, 0f),
            Food(++id, "Peanut Butter", null, null, 32f, 5.88f, 0.25f, 0.22f, 0.5f, 0.08f),
        )
    }
    fun getFood(id: Int) = foods.first { it.id == id }

    val mealTypes by lazy {
        var id = 0
        listOf(
            MealType(++id, "Breakfast"),
            MealType(++id, "Lunch"),
            MealType(++id, "Dinner"),
            MealType(++id, "Snack"),
        )
    }
    fun getMealType(id: Int) = mealTypes.first { it.id == id }

    val mealTimes by lazy {
        var id = 0
        fun getInstant(hours: Int) = Instant.now().minus(hours.toLong(), ChronoUnit.HOURS)
        listOf(
            MealTime(++id, getMealType(id).id, getInstant(id)),
            MealTime(++id, getMealType(id).id, getInstant(id)),
            MealTime(++id, getMealType(id).id, getInstant(id)),
        )
    }
    fun getMealTime(id: Int) = mealTimes.first { it.id == id }

    val servingAmounts by lazy {
        var id = 0
        listOf(
            ServingAmount(++id, getMealTime(id).id, getFood(id).id, 100f),
            ServingAmount(++id, getMealTime(id).id, getFood(id).id, 100f),
            ServingAmount(++id, getMealTime(id).id, getFood(id).id, 100f),
        )
    }
    fun getServingAmount(id: Int) = servingAmounts.first { it.id == id }

}