package com.bonsai.pantryghost.data

import androidx.lifecycle.SavedStateHandle
import com.bonsai.pantryghost.idArg
import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.ui.food.EditFoodVm

object SampleData {

    val foods by lazy {
        var id = 0
        listOf(
            Food(++id, "Apple", 52f, 0.3f, 13.8f, 0.2f, 2.4f),
            Food(++id, "Chicken Breast", 165f, 31.0f, 0f, 3.6f, 0f),
            Food(++id, "Peanut Butter", 588f, 25.1f, 20.0f, 50.0f, 6.0f),
        )
    }
}