package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.data.dao.FoodDao
import com.bonsai.pantryghost.data.dao.MealDao
import com.bonsai.pantryghost.data.dao.MealTypeDao
import com.bonsai.pantryghost.data.dao.ServingDao
import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealType
import com.bonsai.pantryghost.model.Serving
import kotlinx.coroutines.flow.Flow

class DaoRepository(
    val foodDao: FoodDao,
    val servingDao: ServingDao,
    val mealDao: MealDao,
    val mealTypeDao: MealTypeDao,
) : DataRepository {

    // food
    override fun getAllFoods(): Flow<List<Food>> = foodDao.getAll()
    override fun getFoodById(id: Int): Flow<Food> = foodDao.getById(id)
    override fun getRecentFoodNames(count: Int): Flow<List<String>> = foodDao.getRecentNames(count)
    override suspend fun insertFood(food: Food) = foodDao.insert(food)
    override suspend fun updateFood(food: Food) = foodDao.update(food)
    override suspend fun insertFoods(foods: List<Food>) = foodDao.insertAll(foods)

    // meals
    override fun getAllMeals(): Flow<List<Meal>> = mealDao.getAll()
    override fun getRecentDistinctMeals(count: Int): Flow<List<Meal>> =
        mealDao.getRecentDistinctMeals(count)
    override fun getMealById(id: Int): Flow<Meal> = mealDao.getById(id)

    // mealType
    override fun getMealTypeById(mealTypeId: Int): Flow<MealType> =
        mealTypeDao.getById(mealTypeId)
    override fun getAllMealTypes(): Flow<List<MealType>> = mealTypeDao.getAll()
    override suspend fun insertMealTypes(mealTypes: List<MealType>) = mealTypeDao.insertAll(mealTypes)

    override fun getServingsByMealId(id: Int): Flow<List<Serving>> = servingDao.getByMealId(id)
}