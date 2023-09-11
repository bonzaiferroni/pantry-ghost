package com.bonsai.pantryghost.model

import java.time.Instant

data class Meal (
    val mealTypeId: Int,
    val mealTypeName: String,
    val mealTimeId: Int,
    val mealTime: Instant,
)