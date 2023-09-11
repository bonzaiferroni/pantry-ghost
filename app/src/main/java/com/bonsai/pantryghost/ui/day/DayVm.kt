package com.bonsai.pantryghost.ui.day

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.getLocalDate
import com.bonsai.pantryghost.model.Meal
import com.bonsai.pantryghost.model.Serving
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayVm @Inject constructor(
    savedStateHandle: SavedStateHandle,
    dataRepository: DataRepository,
) : ViewModel() {

    private val date = savedStateHandle.getLocalDate()

    private val _uiState = MutableStateFlow(DayUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            dataRepository.getMealsOnDate(date)
                .combine(dataRepository.getServingsOnDate(date)) { meals, servings ->
                    meals.map { meal ->
                        MealUiState(
                            meal = meal,
                            servings = servings.filter { it.mealTimeId == meal.mealTimeId }
                        )
                    }
                }.collect {
                    _uiState.value = _uiState.value.copy(
                        meals = it
                    )
                }
        }
    }
}

data class DayUiState(
    val meals: List<MealUiState> = emptyList(),
)

data class MealUiState(
    val meal: Meal,
    val servings: List<Serving>,
)