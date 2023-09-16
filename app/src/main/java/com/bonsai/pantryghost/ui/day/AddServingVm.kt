package com.bonsai.pantryghost.ui.day

import androidx.lifecycle.ViewModel
import com.bonsai.pantryghost.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddServingVm @Inject constructor(
    dataRepository: DataRepository,
) : ViewModel() {
    // private val _uiState = MutableStateFlow(AddServingUiState())
    // val uiState = _uiState.asStateFlow()
}

// data class AddServingUiState()