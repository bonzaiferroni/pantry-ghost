package com.bonsai.pantryghost.ui.day

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import com.bonsai.pantryghost.NavRoute
import com.bonsai.pantryghost.data.SampleRepository
import com.bonsai.pantryghost.ui.common.PgIconButton
import com.bonsai.pantryghost.ui.common.PgScaffold
import com.bonsai.pantryghost.utils.gapMedium
import com.bonsai.pantryghost.utils.paddingSmall
import java.time.LocalDate

@Composable
fun DayScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState? = null,
    navController: NavHostController? = null,
    viewModel: DayVm = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    PgScaffold(
        modifier = modifier,
        drawerState = drawerState,
        title = "Day", // TODO: display "Today" or "Yesterday" based on time
    ) {
        // TODO: display aggregate nutritional information
        LazyColumn(
            modifier = Modifier.padding(horizontal = paddingSmall()),
            verticalArrangement = Arrangement.spacedBy(gapMedium()),
        ) {
            items(uiState.meals) { meal ->
                MealCard(
                    mealUiState = meal,
                    viewModel = viewModel,
                )
            }
        }
    }
}

@Composable
fun MealCard(
    mealUiState: MealUiState,
    viewModel: DayVm,
    modifier: Modifier = Modifier,
    navController: NavHostController? = null,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(paddingSmall()),
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = mealUiState.meal.mealTypeName,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    PgIconButton(
                        icon = Icons.Filled.MoreVert,
                        onClick = { }
                    )
                }
            }
            mealUiState.servings.forEach {
                Text(text = it.foodName)
            }
        }
    }
}

@Preview
@Composable
fun DayScreenPreview() {
    val savedStateHandle = SavedStateHandle()
    savedStateHandle[NavRoute.dateArg] = LocalDate.now().toString()
    val vm = DayVm(savedStateHandle, SampleRepository())
    DayScreen(viewModel = vm)
}