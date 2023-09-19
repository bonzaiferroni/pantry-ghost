package com.bonsai.pantryghost.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bonsai.pantryghost.model.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    fun getAll(): Flow<List<Food>>

    @Query("SELECT * FROM food WHERE id = :id")
    fun getById(id: Int): Flow<Food>

    @Query("SELECT DISTINCT name FROM food " +
            "ORDER BY id DESC LIMIT :count")
    fun getRecentNames(count: Int): Flow<List<String>>

    // get foods by recently added servings
    @Query("""
        SELECT * FROM food INNER JOIN serving_amount
        ON food.id=serving_amount.food_id
        ORDER BY serving_amount.id DESC LIMIT :count
    """)
    fun getRecentFoods(count: Int): Flow<List<Food>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(food: Food): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(foods: List<Food>)

    @Update
    suspend fun update(food: Food)

    @Delete
    suspend fun delete(food: Food)

    @Query("DELETE FROM food")
    suspend fun deleteAll()
}