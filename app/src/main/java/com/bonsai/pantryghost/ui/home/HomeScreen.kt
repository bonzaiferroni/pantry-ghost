package com.bonsai.pantryghost.ui.home

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import com.bonsai.pantryghost.ui.common.PgScaffold
import com.bonsai.pantryghost.ui.scan.BasicBarcodeScanner

@Composable
fun HomeScreen(drawerState: DrawerState) {
    PgScaffold(
        title = "Home",
        drawerState = drawerState
    ) {
        /*Column(
            modifier = Modifier
                .padding(paddingSmall())
        ) {
            Text("Hello Pantry \uD83D\uDC7B Ghost!")
        }*/
        BasicBarcodeScanner()
    }
}