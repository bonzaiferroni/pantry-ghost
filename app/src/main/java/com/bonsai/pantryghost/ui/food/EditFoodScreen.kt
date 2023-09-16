package com.bonsai.pantryghost.ui.food

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import com.bonsai.pantryghost.NavRoute.Companion.idArg
import com.bonsai.pantryghost.data.SampleRepository
import com.bonsai.pantryghost.ui.common.AcceptCancelButtons
import com.bonsai.pantryghost.ui.common.PgScaffold
import com.bonsai.pantryghost.ui.common.ValueField
import com.bonsai.pantryghost.utils.gapMedium
import com.bonsai.pantryghost.utils.paddingSmall

@Composable
fun EditFoodScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState? = null,
    navController: NavHostController? = null,
    viewModel: EditFoodVm = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    PgScaffold(
        title = "Edit Food",
        drawerState = drawerState,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(gapMedium()),
            modifier = Modifier
                .padding(paddingSmall())
                .fillMaxWidth()
                .weight(1f)
        ) {
            ValueField(
                value = uiState.name,
                label = "Name",
                keyboardType = KeyboardType.Text,
                onValueChange = viewModel::onNameChange,
            )
            ValueField(
                value = uiState.calories,
                label = "Calories",
                keyboardType = KeyboardType.Decimal,
                onValueChange = viewModel::onCaloriesChange,
            )
            ValueField(
                value = uiState.protein,
                label = "Protein",
                keyboardType = KeyboardType.Decimal,
                onValueChange = viewModel::onProteinChange,
            )
            ValueField(
                value = uiState.carbs,
                label = "Carbs",
                keyboardType = KeyboardType.Decimal,
                onValueChange = viewModel::onCarbsChange,
            )
            ValueField(
                value = uiState.fat,
                label = "Fat",
                keyboardType = KeyboardType.Decimal,
                onValueChange = viewModel::onFatChange,
            )
            ValueField(
                value = uiState.fiber,
                label = "Fiber",
                keyboardType = KeyboardType.Decimal,
                onValueChange = viewModel::onFiberChange,
            )
            if (!uiState.isNewFood) {
                Button(
                    onClick = { viewModel.deleteFood {
                        navController?.navigateUp()
                    }},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                ) {
                    Text(text = "Delete")
                }
            }
            AcceptCancelButtons(
                enabled = uiState.isValid,
                onAccept = {
                    viewModel.saveFood()
                    navController?.navigateUp()
                },
                onCancel = { navController?.navigateUp() }
            )
        }
    }
}

@Preview
@Composable
fun EditFoodScreenPreview() {
    val savedStateHandle = SavedStateHandle(mapOf(idArg to 1))
    val editFoodVm = EditFoodVm(savedStateHandle, SampleRepository())
    EditFoodScreen(viewModel = editFoodVm)
}