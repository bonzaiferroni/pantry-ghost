package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.FoodTag
import com.bonsai.pantryghost.model.FoodTagJoin
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealTime
import com.bonsai.pantryghost.model.MealType
import com.bonsai.pantryghost.model.Serving
import com.bonsai.pantryghost.model.ServingAmount
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface DataRepository {

    // food
    fun getAllFoods(): Flow<List<Food>>
    fun getFoodById(id: Int): Flow<Food>
    suspend fun insertFood(food: Food): Int
    suspend fun updateFood(food: Food)
    suspend fun insertFoods(foods: List<Food>)
    suspend fun deleteFood(food: Food)

    // meal time
    fun getAllMealTimes(): Flow<List<MealTime>>
    fun getMealTimeById(id: Int): Flow<MealTime>
    fun getMealTimesOnDate(date: LocalDate): Flow<List<MealTime>>
    suspend fun insertMealTime(mealTime: MealTime): Int
    suspend fun insertMealTimes(sampleMealTimes: List<MealTime>)
    suspend fun updateMealTime(mealTime: MealTime)

    // meal type
    fun getMealTypeById(mealTypeId: Int): Flow<MealType>
    fun getAllMealTypes(): Flow<List<MealType>>
    suspend fun insertMealTypes(mealTypes: List<MealType>)

    // serving amount
    fun getServingAmountsById(id: Int): Flow<List<ServingAmount>>
    fun getAllServingAmounts(): Flow<List<ServingAmount>>
    suspend fun insertServingAmounts(servingAmounts: List<ServingAmount>)
    suspend fun updateServingAmounts(servingAmounts: List<ServingAmount>)
    suspend fun deleteServingAmount(servingAmount: ServingAmount)

    // meal
    fun getMealsOnDate(date: LocalDate): Flow<List<Meal>>

    // serving
    fun getServingsOnDate(date: LocalDate): Flow<List<Serving>>

    // food tag
    fun getTagsByFoodId(foodId: Int): Flow<List<FoodTag>>
    suspend fun insertFoodTagOrGetId(tag: String): Int
    suspend fun insertFoodTagJoin(foodTagJoin: FoodTagJoin)
    suspend fun addTagToFood(foodId: Int, tagName: String)
    suspend fun removeTagFromFood(foodId: Int, tagId: Int)
}