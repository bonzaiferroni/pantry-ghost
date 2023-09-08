package com.bonsai.pantryghost.ui.meal

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bonsai.pantryghost.NavRoute
import com.bonsai.pantryghost.navigateRoute
import com.bonsai.pantryghost.ui.common.FabParams
import com.bonsai.pantryghost.ui.common.PgIconButton
import com.bonsai.pantryghost.ui.common.PgScaffold
import com.bonsai.pantryghost.utils.gapMedium
import com.bonsai.pantryghost.utils.paddingMedium
import com.bonsai.pantryghost.utils.paddingSmall

@Composable
fun MealScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState? = null,
    navController: NavHostController? = null,
    viewModel: MealVm = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    PgScaffold(
        title = "Meals",
        drawerState = drawerState,
        fabParams = FabParams(Icons.Filled.Add, "Add meal") {
            navController?.navigateRoute(NavRoute.EditMeal, 0)
        },
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.padding(paddingMedium()),
            verticalArrangement = Arrangement.spacedBy(gapMedium())
        ) {
            items(uiState.meals) { meal ->
                Card() {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(paddingSmall()),
                    ) {
                        Text(meal.name)
                        Spacer(modifier = Modifier.weight(1f))
                        PgIconButton(
                            icon = Icons.Default.Edit,
                            onClick = {
                                navController?.navigateRoute(NavRoute.EditMeal, meal.id)
                            }
                        )
                    }
                }
            }
        }
    }
}