package com.bonsai.pantryghost.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bonsai.pantryghost.model.MealTime
import kotlinx.coroutines.flow.Flow


@Dao
interface MealTimeDao {
    @Query("SELECT * FROM meal_time")
    fun getAll(): Flow<List<MealTime>>

    @Query("SELECT * FROM meal_time WHERE id = :id")
    fun getById(id: Int): Flow<MealTime>

    @Query("SELECT * FROM meal_time WHERE time >= :dayStartMillis AND time < :dayEndMillis")
    fun getMealsOnDate(dayStartMillis: Long, dayEndMillis: Long): Flow<List<MealTime>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mealTime: MealTime): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mealTimes: List<MealTime>)

    @Update
    suspend fun update(mealTime: MealTime)

    @Delete
    suspend fun delete(mealTime: MealTime)

    @Query("DELETE FROM meal_time")
    suspend fun deleteAll()
}