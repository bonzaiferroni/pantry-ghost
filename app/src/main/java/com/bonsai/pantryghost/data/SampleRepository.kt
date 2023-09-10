package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealType
import com.bonsai.pantryghost.model.Serving
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SampleRepository : DataRepository {

    // food
    override fun getAllFoods(): Flow<List<Food>> = flowOf(SampleData.foods)
    override fun getFoodById(id: Int): Flow<Food> = flowOf(SampleData.getFood(id))
    override fun getRecentFoodNames(count: Int): Flow<List<String>> =
        flowOf(SampleData.foods.map { it.name }.take(count))
    override suspend fun insertFood(food: Food): Long = TODO("Not yet implemented")
    override suspend fun updateFood(food: Food) = TODO("Not yet implemented")
    override suspend fun insertFoods(foods: List<Food>) = TODO("Not yet implemented")

    // meal
    override fun getAllMeals(): Flow<List<Meal>> = flowOf(SampleData.meals)
    override fun getRecentDistinctMeals(count: Int): Flow<List<Meal>> =
        flowOf(SampleData.meals.take(count))
    override fun getMealById(id: Int): Flow<Meal> = flowOf(SampleData.getMeal(id))
    override suspend fun insertMeal(meal: Meal): Int = TODO("Not yet implemented")
    override suspend fun updateMeal(meal: Meal) = TODO("Not yet implemented")

    // mealType
    override fun getMealTypeById(mealTypeId: Int): Flow<MealType> =
        flowOf(SampleData.getMealType(mealTypeId))
    override fun getAllMealTypes(): Flow<List<MealType>> = flowOf(SampleData.mealTypes)
    override suspend fun insertMealTypes(mealTypes: List<MealType>) = TODO("Not yet implemented")

    // serving
    override fun getServingsByMealId(id: Int): Flow<List<Serving>> =
        flowOf(SampleData.servings.filter { it.mealId == id })
    override fun getAllServings(): Flow<List<Serving>> = flowOf(SampleData.servings)
    override suspend fun insertServings(servings: List<Serving>) = TODO("Not yet implemented")
    override suspend fun updateServings(servings: List<Serving>) = TODO("Not yet implemented")
    override suspend fun deleteServing(serving: Serving) = TODO("Not yet implemented")

}