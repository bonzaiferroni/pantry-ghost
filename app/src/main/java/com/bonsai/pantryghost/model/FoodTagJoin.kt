package com.bonsai.pantryghost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "food_tag_join",
    primaryKeys = ["food_id", "food_tag_id"],
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FoodTag::class,
            parentColumns = ["id"],
            childColumns = ["food_tag_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class FoodTagJoin(
    @ColumnInfo(name = "food_id")
    val foodId: Int,
    @ColumnInfo(name = "food_tag_id")
    val tagId: Int,
)