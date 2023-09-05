package com.bonsai.pantryghost.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bonsai.pantryghost.data.dao.FoodDao
import com.bonsai.pantryghost.data.dao.IngredientDao
import com.bonsai.pantryghost.data.dao.MealDao
import com.bonsai.pantryghost.data.dao.MealTypeDao
import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.Ingredient
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealType

@Database(
    entities = [
        Food::class, Ingredient::class, Meal::class, MealType::class,
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun mealDao(): MealDao
    abstract fun mealTypeDao(): MealTypeDao
}