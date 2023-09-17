package com.bonsai.pantryghost.ui.day

import androidx.lifecycle.ViewModel
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.model.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddServingVm @Inject constructor(
    dataRepository: DataRepository,
) : ViewModel() {
    private val _findFoodState = MutableStateFlow(FindFoodUiState())
    val findFoodState = _findFoodState.asStateFlow()

    private val _addServingState = MutableStateFlow(AddServingUiState())
    val addServingState = _addServingState.asStateFlow()
}

data class FindFoodUiState(
    val foodGroup: String = recentGroupName,
    val foodGroups: List<String> = listOf(recentGroupName),
    val food: Food? = null,
    val foods: List<Food> = listOf(),
)

data class AddServingUiState(
    val food: Food? = null,
    val amount: Float = 0f,
    val recentAmounts: List<Float> = listOf(),
)

const val recentGroupName: String = "Recent"