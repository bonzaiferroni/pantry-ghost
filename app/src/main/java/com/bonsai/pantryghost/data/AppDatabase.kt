package com.bonsai.pantryghost.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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
}