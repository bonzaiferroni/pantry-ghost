package com.bonsai.pantryghost.ui.food

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsai.pantryghost.NavRoute.Companion.idArg
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.FoodTag
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

    private val _uiState = MutableStateFlow(EditFoodUiState())
    val uiState = _uiState.asStateFlow()

    private var food: Food
        get() = uiState.value.food
        set(value) {
            _uiState.value = uiState.value.copy(food = value)
        }

    init {
        viewModelScope.launch {
            val food = dataRepository.getFoodById(foodId).first()
            _uiState.value = uiState.value.copy(food = food)
        }
        viewModelScope.launch {
            dataRepository.getTagsByFoodId(foodId).collect { tags ->
                _uiState.value = uiState.value.copy(foodTags = tags)
            }
        }
    }

    fun onNameChange(name: String) {
        food = food.copy(name = name)
    }

    fun onCaloriesChange(calories: Float) {
        food = food.copy(calories = calories)
    }

    fun onProteinChange(protein: Float) {
        food = food.copy(protein = protein)
    }

    fun onCarbsChange(carbs: Float) {
        food = food.copy(carbs = carbs)
    }

    fun onFatChange(fat: Float) {
        food = food.copy(fat = fat)
    }

    fun onFiberChange(fiber: Float) {
        food = food.copy(fiber = fiber)
    }

    fun saveFood() {
        if (!uiState.value.isValid) {
            return
        }
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
            dataRepository.deleteFood(Food(foodId))
            callback()
        }
    }

    fun removeFoodTag(foodTag: FoodTag) {
        viewModelScope.launch {
            dataRepository.removeTagFromFood(foodId, foodTag.id)
        }
    }

    fun onServingSizeChange(servingSize: Float) {
        food = food.copy(servingSize = servingSize)
    }

    fun addFoodTag() {
        _uiState.value = uiState.value.copy(addingTag = true)
    }

    fun onNewFoodTagChange(newFoodTag: String) {
        _uiState.value = uiState.value.copy(newFoodTag = newFoodTag)
    }

    fun onNewTagDialogDismiss(isAccepted: Boolean) {
        _uiState.value = uiState.value.copy(
            addingTag = false,
            newFoodTag = "",
        )
        val newFoodTag = uiState.value.newFoodTag
        if (isAccepted && newFoodTag.isNotBlank()) {
            viewModelScope.launch {
                dataRepository.addTagToFood(foodId, newFoodTag)
            }
        }
    }
}

data class EditFoodUiState(
    val food: Food = Food(),
    val foodTags: List<FoodTag> = emptyList(),
    val addingTag: Boolean = false,
    val newFoodTag: String = "",
) {
    val isValid: Boolean get() = true
    val isNewFood: Boolean get() = food.id == 0
}