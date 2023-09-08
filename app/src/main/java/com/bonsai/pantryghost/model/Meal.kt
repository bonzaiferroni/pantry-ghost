package com.bonsai.pantryghost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "meal",
    foreignKeys = [
        ForeignKey(
            entity = MealType::class,
            parentColumns = ["id"],
            childColumns = ["meal_type_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("meal_type_id")]
)
data class Meal (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    @ColumnInfo(name = "meal_type_id")
    val mealTypeId: Int,
)