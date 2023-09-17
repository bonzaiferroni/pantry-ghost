package com.bonsai.pantryghost.ui.food

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bonsai.pantryghost.ui.common.PgScaffold
import com.bonsai.pantryghost.ui.scan.BarcodeScannerView
import com.bonsai.pantryghost.utils.Paddings

@Composable
fun ScanFoodScreen (
    modifier: Modifier = Modifier,
    drawerState: DrawerState? = null,
    viewModel: ScanFoodVm = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    PgScaffold(
        modifier = modifier,
        drawerState = drawerState,
        title = "Haunt Pantry",
    ) {
        BarcodeScannerView(viewModel::onNewBarcodes)
        LazyColumn(
            modifier = Modifier.padding(horizontal = Paddings.small()),
        ) {
            items(uiState.newFoods) { food ->
                val text = "${food.name}: ${food.calories * 100} cals, " +
                        "${food.carbs * 100} carbs, " +
                        "${food.fat * 100} fat, ${food.protein * 100} protein, " +
                        "${food.fiber * 100} fiber"
                Text(text)
            }
        }
    }

}