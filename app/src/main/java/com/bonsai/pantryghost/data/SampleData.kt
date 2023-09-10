package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealType
import com.bonsai.pantryghost.model.Serving
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

    val mealTypes by lazy {
        var id = 0
        listOf(
            MealType(++id, "Breakfast"),
            MealType(++id, "Lunch"),
            MealType(++id, "Dinner"),
            MealType(++id, "Snack"),
        )
    }

    val meals by lazy {
        var id = 0
        fun getInstant(hours: Int) = Instant.now().minus(hours.toLong(), ChronoUnit.HOURS)
        listOf(
            Meal(++id, "apple breakfast", mealTypes[id].id, getInstant(id)),
            Meal(++id, "chicken lunch", mealTypes[id].id, getInstant(id)),
            Meal(++id, "peanut-butter-jelly-time", mealTypes[id].id, getInstant(id)),
        )
    }

    val servings by lazy {
        var id = 0
        listOf(
            Serving(++id, meals[id].id, foods[id].id, 1f),
            Serving(++id, meals[id].id, foods[id].id, 1f),
            Serving(++id, meals[id].id, foods[id].id, 1f),
        )
    }

}