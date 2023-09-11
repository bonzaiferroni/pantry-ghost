package com.bonsai.pantryghost.ui.home

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import com.bonsai.pantryghost.ui.common.PgScaffold

@Composable
fun HomeScreen(drawerState: DrawerState) {
    PgScaffold(
        title = "Home",
        drawerState = drawerState
    ) {
        CameraScreen()
    }
}