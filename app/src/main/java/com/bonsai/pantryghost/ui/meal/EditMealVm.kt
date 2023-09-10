package com.bonsai.pantryghost.ui.meal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsai.pantryghost.NavRoute
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.MealType
import com.bonsai.pantryghost.model.Serving
import com.bonsai.pantryghost.ui.common.nullPickerText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.Instant
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
                    mealTypes = mealTypes + initialMealType,
                )
            }
        }

        // get foods
        viewModelScope.launch {
            dataRepository.getAllFoods().collect { foods ->
                _uiState.value = uiState.value.copy(
                    foods = foods + initialFood,
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

    fun onMealTypeChange(mealType: MealType) {
        _uiState.value = uiState.value.copy(
            mealType = mealType,
        )
    }

    fun onAddServing() {
        val food = uiState.value.food
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

    fun onFoodChange(food: Food) {
        _uiState.value = uiState.value.copy(
            food = food,
        )
    }

    fun onDeleteServing(serving: ServingUiState) {
        _uiState.value = uiState.value.copy(
            servings = uiState.value.servings.filter { it != serving }
        )
        if (serving.id != 0) {
            viewModelScope.launch {
                val deletedServing = Serving(
                    id = serving.id,
                    mealId = id,
                    foodId = serving.foodId,
                    grams = serving.grams.toFloat(),
                )
                dataRepository.deleteServing(deletedServing)
            }
        }
    }

    fun onSave() {
        if (!uiState.value.isValid) {
            return
        }

        val meal = Meal(
            id = id,
            name = uiState.value.mealName,
            mealTypeId = uiState.value.mealType.id,
            Instant.now(),
        )

        val servings = uiState.value.servings.map { serving ->
            Serving(
                id = serving.id,
                mealId = meal.id,
                foodId = serving.foodId,
                grams = serving.grams.toFloat(),
            )
        }

        viewModelScope.launch {
            if (meal.id == 0) {
                val newMealId = dataRepository.insertMeal(meal)
                dataRepository.insertServings(servings.map { it.copy(mealId = newMealId) })
            } else {
                val newServings = servings.filter { it.id == 0 }
                val editedServings = servings.filter { it.id != 0 }
                dataRepository.updateMeal(meal)
                dataRepository.insertServings(newServings)
                dataRepository.updateServings(editedServings)
            }
        }
    }

    fun onCancel() {
    }
}

val initialMealType = MealType(0, "(meal type)")
val initialFood = Food(0, "(food)", 0f, 0f, 0f, 0f, 0f)

data class EditMealUiState(
    // meal
    val meal: Meal? = null,

    // meal name
    val mealName: String = "",
    val mealNameState: String = nullPickerText,
    val mealNameSuggestions: List<String> = listOf(nullPickerText),

    // meal type
    val mealType: MealType = initialMealType,
    val mealTypes: List<MealType> = listOf(initialMealType),

    // servings
    val servings: List<ServingUiState> = emptyList(),

    // add serving
    val food: Food = initialFood,
    val foods: List<Food> = listOf(initialFood),
    val grams: String = "",
) {
    val isNewMeal: Boolean
        get() = meal == null

    val isValid: Boolean
        get() = mealName.isNotBlank() && mealType != initialMealType && servings.isNotEmpty()
                && servings.all { it.isValid }
    val isNewServingValid: Boolean
        get() = food != initialFood

    val isMealValid: Boolean
        get() = mealName.isNotBlank() && mealType != initialMealType && servings.isNotEmpty()
}

data class ServingUiState(
    val id: Int = 0,
    val foodId: Int,
    val foodName: String = "",
    val grams: String = "",
) {
    val isValid: Boolean
        get() = foodId != 0 && grams.toFloatOrNull() != null
}