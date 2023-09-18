package com.bonsai.pantryghost.ui.day

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bonsai.pantryghost.ui.common.PgScaffold

@Composable
fun AddServingScreen(
    modifier: Modifier = Modifier,
    drawerState: DrawerState? = null,
    navHostController: NavHostController? = null,
) {
    PgScaffold(
        drawerState = drawerState,
        modifier = modifier,
        title = "Add serving",
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {

        }
    }
}