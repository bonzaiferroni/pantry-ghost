package com.bonsai.pantryghost.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val barcode: String?,
    val calories: Float,
    val protein: Float,
    val carbs: Float,
    val fat: Float,
    val fiber: Float,
) {
    override fun toString() = name
}