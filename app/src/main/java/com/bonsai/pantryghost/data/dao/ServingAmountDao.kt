package com.bonsai.pantryghost.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bonsai.pantryghost.model.ServingAmount
import kotlinx.coroutines.flow.Flow


@Dao
interface ServingAmountDao {
    @Query("SELECT * FROM serving_amount")
    fun getAll(): Flow<List<ServingAmount>>

    @Query("SELECT * FROM serving_amount WHERE id = :id")
    fun getById(id: Int): Flow<ServingAmount>

    @Query("SELECT * FROM serving_amount WHERE meal_time_id = :id")
    fun getByMealTimeId(id: Int): Flow<List<ServingAmount>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(servingAmount: ServingAmount): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(servingAmounts: List<ServingAmount>)

    @Update
    suspend fun update(servingAmount: ServingAmount)

    @Update
    suspend fun updateAll(servingAmounts: List<ServingAmount>)

    @Delete
    suspend fun delete(servingAmount: ServingAmount)

    @Query("DELETE FROM serving_amount")
    suspend fun deleteAll()
}