package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.model.Food
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

}