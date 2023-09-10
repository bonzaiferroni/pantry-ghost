package com.bonsai.pantryghost.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bonsai.pantryghost.data.dao.FoodDao
import com.bonsai.pantryghost.data.dao.MealDao
import com.bonsai.pantryghost.data.dao.MealTypeDao
import com.bonsai.pantryghost.data.dao.ServingDao
import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealType
import com.bonsai.pantryghost.model.Serving

@Database(
    entities = [
        Food::class, Serving::class, Meal::class, MealType::class,
    ],
    version = 2
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
    abstract fun servingDao(): ServingDao
    abstract fun mealDao(): MealDao
    abstract fun mealTypeDao(): MealTypeDao
}