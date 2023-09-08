package com.bonsai.pantryghost.ui.meal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsai.pantryghost.NavRoute
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealType
import com.bonsai.pantryghost.ui.common.nullPickerText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditMealVm @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val id: Int = checkNotNull(savedStateHandle[NavRoute.idArg])

    private val _uiState = MutableStateFlow(EditMealUiState())
    val uiState = _uiState.asStateFlow()

    private val _recentMeals: List<Meal> = emptyList()

    init {
        // get meal types
        viewModelScope.launch {
            dataRepository.getAllMealTypes().collect { mealTypes ->
                _uiState.value = uiState.value.copy(
                    mealTypes = mealTypes,
                    mealTypeState = mealTypes.firstOrNull()?.name ?: nullPickerText,
                    mealTypeSuggestions = mealTypes.map { mealType -> mealType.name }
                )
            }
        }
        // get meal from Id
        viewModelScope.launch {
            if (id != 0) {
                // get meal
                val meal = dataRepository.getMealById(id).first()
                _uiState.value = uiState.value.copy(
                    isNewMeal = false,
                    mealName = meal.name,
                    mealNameState = meal.name,
                )
                // get meal type
                val mealType = dataRepository.getMealTypeById(meal.mealTypeId).first()
                _uiState.value = uiState.value.copy(
                    mealTypeState = mealType.name,
                )
            } else {
                _uiState.value = uiState.value.copy(
                    isNewMeal = true,
                )
            }
        }
    }

    fun onMealTypeChange(name: String) {
        _uiState.value = uiState.value.copy(
            mealTypeState = name,
        )
    }

    fun onAddIngredient() {
        _uiState.value = uiState.value.copy(
            ingredients = uiState.value.ingredients + IngredientInfo()
        )
    }
}

data class EditMealUiState(
    val isNewMeal: Boolean = false,
    val mealName: String = "",
    val mealNameState: String = nullPickerText,

    val mealTypes: List<MealType> = emptyList(),
    val mealTypeState: String = nullPickerText,
    val mealTypeSuggestions: List<String> = emptyList(),
    val enableAccept: Boolean = false,
    val ingredients: List<IngredientInfo> = emptyList(),
)

data class IngredientInfo(
    val foodName: String = nullPickerText,
    val foodId: Int? = null,
)