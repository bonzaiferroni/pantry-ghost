package com.bonsai.pantryghost.ui.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.data.usda.UsdaRepository
import com.bonsai.pantryghost.model.Food
import com.bonsai.pantryghost.model.FoodTagJoin
import com.google.mlkit.vision.barcode.common.Barcode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanFoodVm @Inject constructor(
    private val dataRepository: DataRepository,
    private val usdaRepository: UsdaRepository,
) : ViewModel() {

    private val barcodes = mutableListOf<String>()

    private val _uiState = MutableStateFlow(ScanFoodUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            dataRepository.getAllFoods().first().forEach { food ->
                barcodes.add(food.barcode ?: return@forEach)
            }
        }
    }

    fun onNewBarcodes(barcodes: List<Barcode>) {
        barcodes.forEach { barcode ->
            val barcodeValue = barcode.rawValue ?: return@forEach
            if (!this.barcodes.any { it == barcodeValue }) {
                _uiState.value = uiState.value.copy(
                    scanCount = uiState.value.scanCount + 1,
                )
                this.barcodes.add(barcodeValue)
                searchBarcode(barcodeValue)
            }
        }
    }

    private fun searchBarcode(barcode: String) {
        viewModelScope.launch {
            val foodInfo = usdaRepository.searchFoodsByBarcode(barcode)
            if (foodInfo != null) {
                // insert food
                val foodId = dataRepository.insertFood(foodInfo.food)
                val tagId: Int = dataRepository.insertFoodTagOrGetId(foodInfo.category)
                dataRepository.insertFoodTagJoin(FoodTagJoin(foodId, tagId))

                _uiState.value = uiState.value.copy(
                    newFoods = uiState.value.newFoods + foodInfo.food,
                )
            }
        }
    }
}

data class ScanFoodUiState(
    val scanCount: Int = 0,
    val newFoods: List<Food> = emptyList(),
)
