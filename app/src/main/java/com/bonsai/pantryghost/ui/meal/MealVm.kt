package com.bonsai.pantryghost.ui.meal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealVm @Inject constructor(
    private val dataRepository: DataRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MealUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            dataRepository.getAllMeals().collect { meals ->
                _uiState.value = uiState.value.copy(
                    meals = meals,
                )
            }
        }
    }
}

data class MealUiState(
    val meals: List<Meal> = emptyList(),
)
