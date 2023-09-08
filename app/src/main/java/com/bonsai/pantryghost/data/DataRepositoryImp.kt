package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.data.dao.FoodDao
import com.bonsai.pantryghost.data.dao.IngredientDao
import com.bonsai.pantryghost.data.dao.MealDao
import com.bonsai.pantryghost.data.dao.MealTypeDao
import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealType
import kotlinx.coroutines.flow.Flow

class DataRepositoryImp(
    val foodDao: FoodDao,
    val ingredientDao: IngredientDao,
    val mealDao: MealDao,
    val mealTypeDao: MealTypeDao
) : DataRepository {

    // food
    override fun getAllFoods(): Flow<List<Food>> = foodDao.getAll()
    override fun getFoodById(id: Int): Flow<Food> = foodDao.getById(id)
    override fun getRecentFoodNames(count: Int): Flow<List<String>> = foodDao.getRecentNames(count)
    override suspend fun insertFood(food: Food) = foodDao.insert(food)
    override suspend fun updateFood(food: Food) = foodDao.update(food)
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

    override fun getAllMealTypes(): Flow<List<MealType>> = mealTypeDao.getAll()
}