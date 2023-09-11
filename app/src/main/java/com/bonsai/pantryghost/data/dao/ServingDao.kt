package com.bonsai.pantryghost.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.bonsai.pantryghost.model.Serving
import kotlinx.coroutines.flow.Flow

@Dao
interface ServingDao {
    @Query("""
        SELECT 
            food.id AS foodId, 
            serving_amount.id AS servingId,
            serving_amount.meal_time_id AS mealTimeId,
            food.name AS foodName,
            serving_amount.grams,
            food.calories * serving_amount.grams AS calories,
            food.protein * serving_amount.grams AS protein,
            food.carbs * serving_amount.grams AS carbs,
            food.fat * serving_amount.grams AS fat,
            food.fiber * serving_amount.grams AS fiber
        FROM food
        INNER JOIN serving_amount ON food.id = serving_amount.food_id
        INNER JOIN meal_time ON serving_amount.meal_time_id = meal_time.id
        WHERE meal_time.time >= :dayStartMillis AND meal_time.time < :dayEndMillis
        """)
    fun getServingsOnDate(dayStartMillis: Long, dayEndMillis: Long): Flow<List<Serving>>
}