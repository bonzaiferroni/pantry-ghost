package com.bonsai.pantryghost

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bonsai.pantryghost.ui.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.name
    ) {
        composable(NavRoute.Home.name) {
            HomeScreen(drawerState)
        }
        composable(NavRoute.Settings.name) {
            // SettingsScreen(drawerState)
        }
        composable(NavRoute.About.name) {
            // AboutScreen(drawerState)
        }
    }
}

// available routes for the main route
sealed interface NavRoute {
    val name: String

    data object Home : NavRoute {
        override val name = "home"
    }

    data object Settings : NavRoute {
        override val name = "settings"
    }

    data object About : NavRoute {
        override val name = "about"
    }
}