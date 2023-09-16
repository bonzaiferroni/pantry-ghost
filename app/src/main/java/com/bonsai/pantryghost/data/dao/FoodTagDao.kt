package com.bonsai.pantryghost.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bonsai.pantryghost.model.FoodTag
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodTagDao {
    @Query("SELECT * FROM food_tag")
    fun getAll(): Flow<List<FoodTag>>

    @Query("SELECT * FROM food_tag WHERE id=:id")
    fun getById(id: Int): Flow<FoodTag>

    @Insert
    suspend fun insert(foodTag: FoodTag): Long

    @Insert
    suspend fun insertAll(vararg foodTags: FoodTag): Long

    @Update
    suspend fun update(foodTag: FoodTag): Long

    @Update
    suspend fun updateAll(vararg foodTags: FoodTag): Long

    @Query("DELETE FROM food_tag")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteById(id: Int)
}