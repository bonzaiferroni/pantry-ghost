package com.bonsai.pantryghost.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(
    tableName = "meal_time",
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
data class MealTime (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "meal_type_id")
    val mealTypeId: Int,
    val time: Instant,
)