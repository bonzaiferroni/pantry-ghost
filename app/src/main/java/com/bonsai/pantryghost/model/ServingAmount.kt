package com.bonsai.pantryghost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "serving_amount",
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MealTime::class,
            parentColumns = ["id"],
            childColumns = ["meal_time_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("food_id"), Index("meal_time_id")]
)
data class ServingAmount(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "food_id")
    val foodId: Int,
    @ColumnInfo(name = "meal_time_id")
    val mealTimeId: Int,
    val grams: Float,
)