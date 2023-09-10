package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealType
import com.bonsai.pantryghost.model.Serving
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    // food
    fun getAllFoods(): Flow<List<Food>>
    fun getFoodById(id: Int): Flow<Food>
    fun getRecentFoodNames(count: Int): Flow<List<String>>
    suspend fun insertFood(food: Food): Long
    suspend fun updateFood(food: Food)
    suspend fun insertFoods(foods: List<Food>)

    // meal
    fun getAllMeals(): Flow<List<Meal>>
    fun getRecentDistinctMeals(count: Int): Flow<List<Meal>>
    fun getMealById(id: Int): Flow<Meal>

    // mealType
    fun getMealTypeById(mealTypeId: Int): Flow<MealType>
    fun getAllMealTypes(): Flow<List<MealType>>
    suspend fun insertMealTypes(mealTypes: List<MealType>)

    // serving
    fun getServingsByMealId(id: Int): Flow<List<Serving>>
}