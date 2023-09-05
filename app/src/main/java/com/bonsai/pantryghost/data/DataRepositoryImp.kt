package com.bonsai.pantryghost.data

import com.bonsai.pantryghost.data.dao.FoodDao
import com.bonsai.pantryghost.data.dao.IngredientDao
import com.bonsai.pantryghost.data.dao.MealDao
import com.bonsai.pantryghost.data.dao.MealTypeDao

class DataRepositoryImp(
    val foodDao: FoodDao,
    val ingredientDao: IngredientDao,
    val mealDao: MealDao,
    val mealTypeDao: MealTypeDao
) : DataRepository {
}