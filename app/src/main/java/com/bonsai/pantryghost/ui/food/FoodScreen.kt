package com.bonsai.pantryghost.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bonsai.pantryghost.EditFoodRoute
import com.bonsai.pantryghost.navigateRoute
import com.bonsai.pantryghost.ui.common.FabParams
import com.bonsai.pantryghost.ui.common.PgScaffold
import com.bonsai.pantryghost.utils.paddingMedium

@Composable
fun FoodScreen(
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
    viewModel: FoodVm = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    PgScaffold(
        title = "Food",
        drawerState = drawerState,
        fabParams = FabParams(Icons.Filled.Add, "Add food") {
            navController?.navigateRoute(EditFoodRoute, 0)
        },
        modifier = modifier
    ) {
        LazyColumn(modifier = Modifier.padding(paddingMedium())) {
            items(uiState.foods) { food ->
                Text(food.name)
            }
        }
    }
}