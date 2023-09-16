package com.bonsai.pantryghost.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String? = null,
    val description: String? = null,
    val barcode: String? = null,
    val servingSize: Float = 0f,
    val calories: Float = 0f,
    val protein: Float = 0f,
    val carbs: Float = 0f,
    val fat: Float = 0f,
    val fiber: Float = 0f,
) {
    override fun toString() = name ?: description ?: "Food #$id"
}