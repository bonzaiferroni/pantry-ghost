package com.bonsai.pantryghost.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_type")
data class MealType (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
) {
    override fun toString(): String = name
}