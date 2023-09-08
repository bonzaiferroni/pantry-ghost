package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SampleRepository : DataRepository {

    override fun getAllFoods(): Flow<List<Food>> = flowOf(SampleData.foods)

    override fun getFoodById(id: Int): Flow<Food> = flowOf(SampleData.foods[id])

    override fun getRecentFoodNames(count: Int): Flow<List<String>> =
        flowOf(SampleData.foods.map { it.name }.take(count))

    override suspend fun insertFood(food: Food): Long {
        TODO("Not yet implemented")
    }

    override suspend fun updateFood(food: Food) {
        TODO("Not yet implemented")
    }

    override fun getAllMeals(): Flow<List<Meal>> {
        TODO("Not yet implemented")
    }

    override fun getRecentDistinctMeals(count: Int): Flow<List<Meal>> {
        TODO("Not yet implemented")
    }

    override fun getMealById(id: Int): Flow<Meal> {
        TODO("Not yet implemented")
    }

    override fun getMealTypeById(mealTypeId: Int): Flow<MealType> {
        TODO("Not yet implemented")
    }

    override fun getAllMealTypes(): Flow<List<MealType>> {
        TODO("Not yet implemented")
    }

}