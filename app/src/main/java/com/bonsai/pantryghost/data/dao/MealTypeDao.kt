package com.bonsai.pantryghost.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bonsai.pantryghost.model.MealType
import kotlinx.coroutines.flow.Flow


@Dao
interface MealTypeDao {
    @Query("SELECT * FROM meal_type")
    fun getAll(): Flow<List<MealType>>

    @Query("SELECT * FROM meal_type WHERE id = :id")
    fun getById(id: Int): Flow<MealType>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mealType: MealType): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mealTypes: List<MealType>)

    @Update
    suspend fun update(mealType: MealType)

    @Delete
    suspend fun delete(mealType: MealType)

    @Query("DELETE FROM meal_type")
    suspend fun deleteAll()
}