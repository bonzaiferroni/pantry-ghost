package com.bonsai.pantryghost.ui.day

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bonsai.pantryghost.ui.common.ItemPicker
import com.bonsai.pantryghost.ui.common.PgScaffold

@Composable
fun AddServingScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState? = null,
    navHostController: NavHostController? = null,
    viewModel: AddServingVm = hiltViewModel(),
) {
    val findFoodState by viewModel.findFoodState.collectAsState()
    val addServingState by viewModel.addServingState.collectAsState()

    PgScaffold(
        drawerState = drawerState,
        modifier = modifier,
        title = "Add serving",
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            ItemPicker(
                pickerState = findFoodState.foodGroup,
                suggestions = findFoodState.foodGroups,
                onValueChange = viewModel::onFoodGroupChange,
            )
        }
    }
}