package com.bonsai.pantryghost.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bonsai.pantryghost.model.Serving
import kotlinx.coroutines.flow.Flow


@Dao
interface ServingDao {
    @Query("SELECT * FROM serving")
    fun getAll(): Flow<List<Serving>>

    @Query("SELECT * FROM serving WHERE id = :id")
    fun getById(id: Int): Flow<Serving>

    @Query("SELECT * FROM serving WHERE meal_id = :id")
    fun getByMealId(id: Int): Flow<List<Serving>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(serving: Serving): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(servings: List<Serving>)

    @Update
    suspend fun update(serving: Serving)

    @Delete
    suspend fun delete(serving: Serving)

    @Query("DELETE FROM serving")
    suspend fun deleteAll()
}