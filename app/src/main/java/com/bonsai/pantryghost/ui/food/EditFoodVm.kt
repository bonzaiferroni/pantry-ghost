package com.bonsai.pantryghost.ui.food

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsai.pantryghost.NavRoute.Companion.idArg
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.model.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditFoodVm @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataRepository: DataRepository
) : ViewModel() {

    private val foodId: Int = checkNotNull(savedStateHandle[idArg])
    private var barcode: String? = null

    private val _uiState = MutableStateFlow(EditFoodUiState())
    val uiState = _uiState.asStateFlow()

    init {
        if (foodId == 0) {
            setParams()
        }
        viewModelScope.launch {
            val food = dataRepository.getFoodById(foodId).first()
            setParams(food)
        }
    }

    private fun setParams(food: Food? = null) {
        barcode = food?.barcode
        _uiState.value = uiState.value.copy(
            isNewFood = foodId == 0,
            name = food?.name ?: "",
            calories = food?.calories?.toString() ?: "",
            protein = food?.protein?.toString() ?: "",
            carbs = food?.carbs?.toString() ?: "",
            fat = food?.fat?.toString() ?: "",
            fiber = food?.fiber?.toString() ?: "",
        )
    }

    fun onNameChange(name: String) {
        _uiState.value = uiState.value.copy(name = name)
    }

    fun onCaloriesChange(calories: String) {
        _uiState.value = uiState.value.copy(calories = calories)
    }

    fun onProteinChange(protein: String) {
        _uiState.value = uiState.value.copy(protein = protein)
    }

    fun onCarbsChange(carbs: String) {
        _uiState.value = uiState.value.copy(carbs = carbs)
    }

    fun onFatChange(fat: String) {
        _uiState.value = uiState.value.copy(fat = fat)
    }

    fun onFiberChange(fiber: String) {
        _uiState.value = uiState.value.copy(fiber = fiber)
    }

    fun saveFood() {
        if (!uiState.value.isValid) {
            return
        }

        val food = Food(
            id = foodId,
            name = uiState.value.name,
            barcode = barcode,
            calories = uiState.value.calories.toFloat(),
            protein = uiState.value.protein.toFloat(),
            carbs = uiState.value.carbs.toFloat(),
            fat = uiState.value.fat.toFloat(),
            fiber = uiState.value.fiber.toFloat(),
        )
        viewModelScope.launch {
            if (foodId == 0) {
                dataRepository.insertFood(food)
            } else {
                dataRepository.updateFood(food)
            }
        }
    }

    fun deleteFood(callback: () -> Unit) {
        viewModelScope.launch {
            dataRepository.deleteFood(Food(foodId, "", null, 0f, 0f, 0f, 0f, 0f))
            callback()
        }
    }
}

data class EditFoodUiState(
    val isNewFood: Boolean = false,
    val name: String = "",
    val calories: String = "",
    val protein: String = "",
    val carbs: String = "",
    val fat: String = "",
    val fiber: String = "",
) {
    val isValid: Boolean
        get() = name.isNotBlank() &&
                calories.isNotBlank() && calories.toFloatOrNull() != null &&
                protein.isNotBlank() && protein.toFloatOrNull() != null &&
                carbs.isNotBlank() && carbs.toFloatOrNull() != null &&
                fat.isNotBlank() && fat.toFloatOrNull() != null &&
                fiber.isNotBlank() && fiber.toFloatOrNull() != null
}