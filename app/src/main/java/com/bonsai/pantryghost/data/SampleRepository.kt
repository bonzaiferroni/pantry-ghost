package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealTime
import com.bonsai.pantryghost.model.MealType
import com.bonsai.pantryghost.model.Serving
import com.bonsai.pantryghost.model.ServingAmount
import com.bonsai.pantryghost.utils.toLocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import java.time.ZoneId

class SampleRepository : DataRepository {

    // food
    override fun getAllFoods(): Flow<List<Food>> = flowOf(SampleData.foods)
    override fun getFoodById(id: Int): Flow<Food> = flowOf(SampleData.getFood(id))
    override fun getRecentFoodNames(count: Int): Flow<List<String>> =
        flowOf(SampleData.foods.map { it.name }.take(count))
    override suspend fun insertFood(food: Food): Long = TODO("Not yet implemented")
    override suspend fun updateFood(food: Food) = TODO("Not yet implemented")
    override suspend fun insertFoods(foods: List<Food>) = TODO("Not yet implemented")

    // meal time
    override fun getAllMealTimes(): Flow<List<MealTime>> = flowOf(SampleData.mealTimes)
    override fun getMealTimeById(id: Int): Flow<MealTime> = flowOf(SampleData.getMealTime(id))
    override fun getMealTimesOnDate(date: LocalDate): Flow<List<MealTime>> {
        val meals = SampleData.mealTimes
            .filter { it.time.atZone(ZoneId.systemDefault()).toLocalDate() == date }
        return flowOf(meals)
    }

    override suspend fun insertMealTime(mealTime: MealTime): Int = TODO("Not yet implemented")
    override suspend fun updateMealTime(mealTime: MealTime) = TODO("Not yet implemented")

    // meal type
    override fun getMealTypeById(mealTypeId: Int): Flow<MealType> =
        flowOf(SampleData.getMealType(mealTypeId))
    override fun getAllMealTypes(): Flow<List<MealType>> = flowOf(SampleData.mealTypes)
    override suspend fun insertMealTypes(mealTypes: List<MealType>) = TODO("Not yet implemented")

    // serving amount
    override fun getServingAmountsById(id: Int): Flow<List<ServingAmount>> =
        flowOf(SampleData.servingAmounts.filter { it.mealTimeId == id })
    override fun getAllServingAmounts(): Flow<List<ServingAmount>> = flowOf(SampleData.servingAmounts)
    override suspend fun insertServingAmounts(servingAmounts: List<ServingAmount>) = TODO("Not yet implemented")
    override suspend fun updateServingAmounts(servingAmounts: List<ServingAmount>) = TODO("Not yet implemented")
    override suspend fun deleteServingAmount(servingAmount: ServingAmount) = TODO("Not yet implemented")

    // meal
    override fun getMealsOnDate(date: LocalDate): Flow<List<Meal>> {
        val meals = SampleData.mealTimes
            .filter { it.time.toLocalDate() == date }
            .map { mealTime ->
                val mealType = SampleData.mealTypes.first { it.id == mealTime.mealTypeId }
                Meal(
                    mealTimeId = mealTime.id,
                    mealTypeId = mealType.id,
                    mealTypeName = mealType.name,
                    mealTime = mealTime.time,
                )
            }
        return flowOf(meals)
    }

    // serving
    override fun getServingsOnDate(date: LocalDate): Flow<List<Serving>> {
        val servings = SampleData.servingAmounts
            .filter { SampleData.getMealTime(it.mealTimeId).time.toLocalDate() == date }
            .map { servingAmount ->
                val food = SampleData.getFood(servingAmount.foodId)
                Serving(
                    foodId = food.id,
                    foodName = food.name,
                    servingId = servingAmount.id,
                    mealTimeId = servingAmount.mealTimeId,
                    grams = servingAmount.grams,
                    calories = food.calories * servingAmount.grams,
                    protein = food.protein * servingAmount.grams,
                    carbs = food.carbs * servingAmount.grams,
                    fat = food.fat * servingAmount.grams,
                    fiber = food.fiber * servingAmount.grams,
                )
            }
        return flowOf(servings)
    }
}