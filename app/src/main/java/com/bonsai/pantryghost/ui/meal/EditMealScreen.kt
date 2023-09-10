package com.bonsai.pantryghost.ui.meal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import com.bonsai.pantryghost.NavRoute
import com.bonsai.pantryghost.data.SampleRepository
import com.bonsai.pantryghost.navigateRoute
import com.bonsai.pantryghost.ui.common.AcceptCancelButtons
import com.bonsai.pantryghost.ui.common.FloatField
import com.bonsai.pantryghost.ui.common.ItemPicker
import com.bonsai.pantryghost.ui.common.PgIconButton
import com.bonsai.pantryghost.ui.common.PgScaffold
import com.bonsai.pantryghost.ui.common.StringField
import com.bonsai.pantryghost.ui.common.StringPickerField
import com.bonsai.pantryghost.ui.common.ValueField
import com.bonsai.pantryghost.utils.gapMedium
import com.bonsai.pantryghost.utils.paddingSmall

@Composable
fun EditMealScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState? = null,
    navController: NavHostController? = null,
    viewModel: EditMealVm = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    PgScaffold(
        title = "Edit Meal",
        drawerState = drawerState,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(gapMedium()),
            modifier = Modifier
                .padding(paddingSmall())
        ) {
            // name
            MealNameField(
                uiState = uiState,
                viewModel = viewModel,
            )
            // meal type
            ItemPicker(
                pickerState = uiState.mealType,
                suggestions = uiState.mealTypes,
                onValueChange = viewModel::onMealTypeChange,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(200.dp),
            )
            // servings
            ServingsList(
                uiState = uiState,
                viewModel = viewModel,
            )
            // add serving
            AddServingControl(
                uiState = uiState,
                viewModel = viewModel,
            )
            // add food
            Button(onClick = { navController?.navigateRoute(NavRoute.EditFoodRoute) }) {
                Text("Add Food")
            }
        }
        AcceptCancelButtons(
            enabled = uiState.isValid,
            onAccept = viewModel::onSave,
            onCancel = viewModel::onCancel,
        )
    }
}

@Composable
fun MealNameField(
    uiState: EditMealUiState,
    viewModel: EditMealVm,
    modifier: Modifier = Modifier,
) {
    if (uiState.isNewMeal) {
        StringPickerField(
            label = "Meal Name",
            value = uiState.mealName,
            pickerState = uiState.mealNameState,
            suggestions = uiState.mealNameSuggestions,
            onValueChange = viewModel::onMealNameChange,
            modifier = modifier,
        )
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier,
        ) {
            StringField(
                value = uiState.mealName,
                label = "Meal Name",
                onValueChange = viewModel::onMealNameChange,
                modifier = Modifier.weight(1f),
            )
            Text(text = uiState.meal?.time.toString())
        }
    }
}

@Composable
fun AddServingControl(
    uiState: EditMealUiState,
    viewModel: EditMealVm,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(gapMedium()),
        modifier = modifier
    ) {
        ItemPicker(
            pickerState = uiState.food,
            suggestions = uiState.foods,
            onValueChange = viewModel::onFoodChange,
            modifier = Modifier.weight(1f),
        )
        ValueField(
            value = uiState.grams,
            label = "grams",
            keyboardType = KeyboardType.Decimal,
            onValueChange = viewModel::onGramsChange,
            modifier = Modifier.weight(1f),
        )
        PgIconButton(
            onClick = viewModel::onAddServing,
            icon = Icons.Default.Add,
            contentDescription = "Add Serving",
        )
    }
}

@Composable
fun ServingsList(
    uiState: EditMealUiState,
    viewModel: EditMealVm,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(uiState.servings) { serving ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(gapMedium()),
            ) {
                Text(
                    text = serving.foodName,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f),
                )
                FloatField(
                    value = serving.grams,
                    label = "grams",
                    onValueChange = { viewModel.onGramsChange(serving.foodId, it) },
                    modifier = Modifier.weight(1f),
                )
                PgIconButton(
                    onClick = { viewModel.onDeleteServing(serving)},
                    icon = Icons.Default.Delete,
                    contentDescription = "Delete Serving"
                )
            }
        }
    }
}

@Preview
@Composable
fun EditMealScreenPreview() {
    val savedStateHandle = SavedStateHandle()
    savedStateHandle[NavRoute.idArg] = 1
    val viewModel = EditMealVm(savedStateHandle, SampleRepository())
    EditMealScreen(viewModel = viewModel)
}