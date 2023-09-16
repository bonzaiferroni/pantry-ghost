package com.bonsai.pantryghost.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bonsai.pantryghost.ui.common.PgScaffold
import com.bonsai.pantryghost.utils.paddingSmall

@Composable
fun HomeScreen(drawerState: DrawerState) {
    PgScaffold(
        title = "Home",
        drawerState = drawerState
    ) {
        Column(
            modifier = Modifier
                .padding(paddingSmall())
        ) {
            Text("Hello Pantry \uD83D\uDC7B Ghost!")
        }
    }
}