package com.bonsai.pantryghost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "ingredient",
    foreignKeys = [
        ForeignKey(
            entity = Food::class,
            parentColumns = ["id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Meal::class,
            parentColumns = ["id"],
            childColumns = ["meal_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("food_id"), Index("meal_id")]
)
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "food_id")
    val foodId: Int,
    @ColumnInfo(name = "meal_id")
    val mealId: Int,
    val grams: Float,
)