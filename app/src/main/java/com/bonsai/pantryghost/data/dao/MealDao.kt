package com.bonsai.pantryghost.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bonsai.pantryghost.model.Meal
import kotlinx.coroutines.flow.Flow


@Dao
interface MealDao {
    @Query("SELECT * FROM meal")
    fun getAll(): Flow<List<Meal>>

    @Query("SELECT * FROM meal WHERE id = :id")
    fun getById(id: Int): Flow<Meal>

    @Query("SELECT t1.* FROM meal t1 " +
            "JOIN (SELECT name, MIN(id) AS MinID FROM meal GROUP BY name) t2 " +
            "ON t1.name = t2.name AND t1.id = t2.MinID " +
            "ORDER BY id DESC LIMIT :count")
    fun getRecentDistinctMeals(count: Int): Flow<List<Meal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal: Meal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(meals: List<Meal>)

    @Update
    suspend fun update(meal: Meal)

    @Delete
    suspend fun delete(meal: Meal)

    @Query("DELETE FROM meal")
    suspend fun deleteAll()
}