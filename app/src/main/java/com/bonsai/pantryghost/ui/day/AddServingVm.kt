package com.bonsai.pantryghost.ui.day

import com.bonsai.pantryghost.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddServingVm @Inject constructor(
    dataRepository: DataRepository,
) {
    private val _uiState = MutableStateFlow(AddServingUiState())
    val uiState = _uiState.asStateFlow()
}

data class AddServingUiState()