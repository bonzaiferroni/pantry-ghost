package com.bonsai.pantryghost.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
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

    @Query("SELECT * FROM food_tag WHERE food_tag.name=:name")
    fun getByName(name: String): Flow<FoodTag>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(foodTag: FoodTag): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(foodTags: List<FoodTag>)

    @Update
    suspend fun update(foodTag: FoodTag)

    @Update
    suspend fun updateAll(foodTags: List<FoodTag>)

    @Query("DELETE FROM food_tag")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteById(foodTag: FoodTag)
}