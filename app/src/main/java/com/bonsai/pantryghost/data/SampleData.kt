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
            Food(++id, "Apple", 52f, 0.3f, 13.8f, 0.2f, 2.4f),
            Food(++id, "Chicken Breast", 165f, 31.0f, 0f, 3.6f, 0f),
            Food(++id, "Peanut Butter", 588f, 25.1f, 20.0f, 50.0f, 6.0f),
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
            ServingAmount(++id, getMealTime(id).id, getFood(id).id, 1f),
            ServingAmount(++id, getMealTime(id).id, getFood(id).id, 1f),
            ServingAmount(++id, getMealTime(id).id, getFood(id).id, 1f),
        )
    }
    fun getServingAmount(id: Int) = servingAmounts.first { it.id == id }

}