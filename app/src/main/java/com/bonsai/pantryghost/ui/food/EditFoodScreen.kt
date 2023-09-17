package com.bonsai.pantryghost.ui.food

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val food = uiState.food

    PgScaffold(
        title = "Edit $food",
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(gapMedium()),
            ) {
                ValueField(
                    value = food.name,
                    label = "Name",
                    onValueChange = viewModel::onNameChange,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = "barcode:\n${food.barcode ?: "nope"}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                )
            }

            food.description?.let {
                Text(text = "Product Description: $it")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(gapMedium()),
            ) {
                if (uiState.addingTag) {
                    // TODO: pop dialog to add tag
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(40)
                        )
                        .clickable { viewModel.addFoodTag() }
                        .padding(paddingSmall())
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add tag",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                uiState.foodTags.forEach { foodTag ->
                    Box(
                        modifier = Modifier
                            .background(color = Color.LightGray, shape = RoundedCornerShape(40))
                            .padding(paddingSmall())
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(gapMedium()),
                        ) {
                            Text(text = foodTag)
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Remove tag",
                                tint = Color.Gray,
                                modifier = Modifier
                                    .size(16.dp)
                                    .clickable { viewModel.removeFoodTag(foodTag) }
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(gapMedium()),
            ) {
                ValueField(
                    value = food.servingSize,
                    label = "Serving Size",
                    onValueChange = viewModel::onServingSizeChange,
                    modifier = Modifier.weight(1f),
                )
                ValueField(
                    value = food.calories,
                    label = "Calories",
                    onValueChange = viewModel::onCaloriesChange,
                    modifier = Modifier.weight(1f),
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(gapMedium()),
            ) {
                ValueField(
                    value = food.protein,
                    label = "Protein",
                    onValueChange = viewModel::onProteinChange,
                    modifier = Modifier.weight(1f),
                )
                ValueField(
                    value = food.carbs,
                    label = "Carbs",
                    onValueChange = viewModel::onCarbsChange,
                    modifier = Modifier.weight(1f),
                )
                ValueField(
                    value = food.fat,
                    label = "Fat",
                    onValueChange = viewModel::onFatChange,
                    modifier = Modifier.weight(1f),
                )
                ValueField(
                    value = food.fiber,
                    label = "Fiber",
                    onValueChange = viewModel::onFiberChange,
                    modifier = Modifier.weight(1f),
                )
            }

            if (!uiState.isNewFood) {
                Button(
                    onClick = {
                        viewModel.deleteFood {
                            navController?.navigateUp()
                        }
                    },
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