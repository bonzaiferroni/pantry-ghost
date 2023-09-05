package com.bonsai.pantryghost.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bonsai.pantryghost.ui.common.PgScaffold
import com.bonsai.pantryghost.utils.paddingMedium
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(drawerState: DrawerState) {
    PgScaffold(
        title = "Home",
        drawerState = drawerState
    ) {
        Column(modifier= Modifier.padding(paddingMedium())) {
            Text("Hello home")
        }
    }
}