package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.model.Food
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    // food
    fun getAllFoods(): Flow<List<Food>>
    fun getFoodById(id: Int): Flow<Food>
    fun getRecentFoodNames(count: Int): Flow<List<String>>
    suspend fun insertFood(food: Food): Long
    suspend fun updateFood(food: Food)
}