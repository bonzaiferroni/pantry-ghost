package com.bonsai.pantryghost.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.FoodTag
import com.bonsai.pantryghost.model.FoodTagJoin
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodTagJoinDao {
    @Query("""
        SELECT * FROM food INNER JOIN food_tag_join
        ON food.id=food_tag_join.food_id
        WHERE food_tag_join.food_tag_id=:tagId
    """)
    fun getFoodsByTagId(tagId: Int): Flow<List<Food>>

    @Query("""
        SELECT * FROM food_tag INNER JOIN food_tag_join
        ON food_tag.id=food_tag_join.food_tag_id
        WHERE food_tag_join.food_id=:foodId
    """)
    fun getTagsByFoodId(foodId: Int): Flow<List<FoodTag>>

    @Insert
    suspend fun insert(foodTagJoin: FoodTagJoin)
}