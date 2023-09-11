package com.bonsai.pantryghost.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bonsai.pantryghost.data.dao.FoodDao
import com.bonsai.pantryghost.data.dao.MealDao
import com.bonsai.pantryghost.data.dao.MealTimeDao
import com.bonsai.pantryghost.data.dao.MealTypeDao
import com.bonsai.pantryghost.data.dao.ServingAmountDao
import com.bonsai.pantryghost.data.dao.ServingDao
import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.MealTime
import com.bonsai.pantryghost.model.MealType
import com.bonsai.pantryghost.model.ServingAmount

@Database(
    entities = [
        Food::class, ServingAmount::class, MealTime::class, MealType::class,
    ],
    version = 3
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
    abstract fun servingAmountDao(): ServingAmountDao
    abstract fun mealTimeDao(): MealTimeDao
    abstract fun mealTypeDao(): MealTypeDao
    abstract fun servingDao(): ServingDao
    abstract fun mealDao(): MealDao
}