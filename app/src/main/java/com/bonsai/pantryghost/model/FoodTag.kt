package com.bonsai.pantryghost.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_tag")
data class FoodTag(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
)