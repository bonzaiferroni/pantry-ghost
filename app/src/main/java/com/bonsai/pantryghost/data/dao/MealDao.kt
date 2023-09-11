package com.bonsai.pantryghost.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.bonsai.pantryghost.model.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {

    @Query("""
        SELECT 
            meal_time.id as mealTimeId,
            meal_time.time as mealTime,
            meal_type.id as mealTypeId,
            meal_type.name as mealTypeName
        FROM meal_time
        INNER JOIN meal_type ON meal_time.meal_type_id = meal_type.id
        WHERE meal_time.time >= :dayStartMillis AND meal_time.time < :dayEndMillis
    """)
    fun getMealsOnDate(dayStartMillis: Long, dayEndMillis: Long): Flow<List<Meal>>
}