package com.bonsai.pantryghost.ui.meal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bonsai.pantryghost.NavRoute
import com.bonsai.pantryghost.navigateRoute
import com.bonsai.pantryghost.ui.common.ItemPicker
import com.bonsai.pantryghost.ui.common.PgIconButton
import com.bonsai.pantryghost.ui.common.PgScaffold
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
                .weight(1f)
        ) {
            ItemPicker(
                pickerState = uiState.mealTypeState,
                suggestions = uiState.mealTypeSuggestions,
                onValueChange = viewModel::onMealTypeChange,
            )
            Button(onClick = { navController?.navigateRoute(NavRoute.EditFoodRoute) }) {
                Text("Add Food")
            }
            PgIconButton(
                onClick = viewModel::onAddIngredient,
                icon = Icons.Default.Add,
                contentDescription = "Add ingredient"
            )
        }
    }
}

@Preview
@Composable
fun EditMealScreenPreview() {
    EditMealScreen()
}