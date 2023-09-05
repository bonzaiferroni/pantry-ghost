package com.bonsai.pantryghost.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PgScaffold(
    title: String,
    drawerState: DrawerState,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        topBar = {
            // to run the animation independently
            val coroutineScope = rememberCoroutineScope()
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            // opens drawer
                            drawerState.open()
                        }
                    }) {
                        Icon(
                            // internal hamburger menu
                            Icons.Rounded.Menu,
                            contentDescription = "MenuButton"
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Surface {
            // padding of the scaffold is enforced to be used
            Column(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        }
    }
}