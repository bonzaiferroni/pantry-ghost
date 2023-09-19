package com.bonsai.pantryghost.ui.day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.model.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddServingVm @Inject constructor(
    val dataRepository: DataRepository,
) : ViewModel() {
    private val _findFoodState = MutableStateFlow(FindFoodUiState())
    val findFoodState = _findFoodState.asStateFlow()

    private val _addServingState = MutableStateFlow(AddServingUiState())
    val addServingState = _addServingState.asStateFlow()

    fun onFoodGroupChange(foodGroup: String) {
        _findFoodState.value = _findFoodState.value.copy(foodGroup = foodGroup)
        viewModelScope.launch {
            val foods = if (foodGroup == recentGroupName) {
                dataRepository.getRecentlyAddedFoods().first()
            } else {
                dataRepository.getFoodsByTagName(foodGroup).first()
            }
            _findFoodState.value = _findFoodState.value.copy(foods = foods)
        }
    }
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