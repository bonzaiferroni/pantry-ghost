package com.bonsai.pantryghost.ui.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.model.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodVm @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FoodUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            dataRepository.getAllFoods().collect {
                _uiState.value = uiState.value.copy(
                    foods = it
                )
            }
        }
    }
}

data class FoodUiState(
    val foods: List<Food> = emptyList()
)