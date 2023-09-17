package com.bonsai.pantryghost.ui.food

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bonsai.pantryghost.NavRoute
import com.bonsai.pantryghost.data.SampleRepository
import com.bonsai.pantryghost.navigateRoute
import com.bonsai.pantryghost.ui.common.FabParams
import com.bonsai.pantryghost.ui.common.PgIconButton
import com.bonsai.pantryghost.ui.common.PgScaffold
import com.bonsai.pantryghost.utils.Gaps
import com.bonsai.pantryghost.utils.Paddings

@Composable
fun FoodScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState? = null,
    navController: NavHostController? = null,
    viewModel: FoodVm = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    PgScaffold(
        title = "Food",
        drawerState = drawerState,
        fabParams = FabParams(Icons.Filled.Add, "Add food") {
            // navController?.navigateRoute(NavRoute.EditFoodRoute, 0)
            navController?.navigateRoute(NavRoute.ScanFoodRoute)
        },
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.padding(Paddings.medium()),
            verticalArrangement = Arrangement.spacedBy(Gaps.medium())
        ) {
            items(uiState.foods) { food ->
                Card() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(Paddings.small()),
                    ) {
                        Text(food.toString())
                        Spacer(modifier = Modifier.weight(1f))
                        PgIconButton(
                            icon = Icons.Default.Edit,
                            onClick = {
                                navController?.navigateRoute(NavRoute.EditFoodRoute, food.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FoodScreenPreview() {
    val viewModel = FoodVm(SampleRepository())
    FoodScreen(viewModel = viewModel)
}
