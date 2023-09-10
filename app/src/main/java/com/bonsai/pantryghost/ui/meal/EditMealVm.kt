package com.bonsai.pantryghost.ui.meal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsai.pantryghost.NavRoute
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.model.Food
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
                )
            }
        }

        // get foods
        viewModelScope.launch {
            dataRepository.getAllFoods().collect { foods ->
                _uiState.value = uiState.value.copy(
                    foods = foods
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
                    mealType = mealType,
                )
                // get servings
                val servings = dataRepository.getServingsByMealId(meal.id).first()
                val foods = dataRepository.getAllFoods().first()
                _uiState.value = uiState.value.copy(
                    foods = foods,
                    servings = servings.map { serving ->
                        ServingUiState(
                            foodId = serving.foodId,
                            foodName = foods.first { it.id == serving.foodId }.name,
                            grams = serving.grams.toString(),
                        )
                    }
                )
            } else {
                _uiState.value = uiState.value.copy(
                    isNewMeal = true,
                )
            }
        }
    }

    fun onMealTypeChange(mealType: MealType?) {
        _uiState.value = uiState.value.copy(
            mealType = mealType,
        )
    }

    fun onAddServing() {
        val food = uiState.value.food ?: throw IllegalStateException("food is null")
        val grams = uiState.value.grams
        _uiState.value = uiState.value.copy(
            servings = uiState.value.servings + ServingUiState(
                foodId = food.id,
                foodName = food.name,
                grams = grams,
            )
        )
    }

    fun onMealNameChange(name: String) {
        _uiState.value = uiState.value.copy(
            mealName = name,
        )
    }

    fun onGramsChange(value: String) {
        _uiState.value = uiState.value.copy(
            grams = value,
        )
    }

    fun onGramsChange(foodId: Int, text: String) {
        _uiState.value = uiState.value.copy(
            servings = uiState.value.servings.map { serving ->
                if (serving.foodId == foodId) {
                    serving.copy(grams = text)
                } else {
                    serving
                }
            }
        )
    }

    fun onFoodChange(food: Food?) {
        _uiState.value = uiState.value.copy(
            food = food,
        )
    }
}

data class EditMealUiState(
    val isNewMeal: Boolean = false,
    val enableAccept: Boolean = false,

    // meal name
    val mealName: String = "",
    val mealNameState: String = nullPickerText,
    val mealNameSuggestions: List<String> = listOf(nullPickerText),

    // meal type
    val mealType: MealType? = null,
    val mealTypes: List<MealType?> = listOf(null),

    // servings
    val servings: List<ServingUiState> = emptyList(),

    // add serving
    val food: Food? = null,
    val foods: List<Food?> = listOf(null),
    val grams: String = "",
)

data class ServingUiState(
    val foodId: Int,
    val foodName: String = "",
    val grams: String = "",
)