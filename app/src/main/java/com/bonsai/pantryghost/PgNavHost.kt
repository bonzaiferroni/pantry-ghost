package com.bonsai.pantryghost

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bonsai.pantryghost.ui.HomeScreen

@Composable
fun PgNavHost(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute.name
    ) {
        composable(HomeRoute.name) {
            HomeScreen(drawerState)
        }
        composable(MealRoute.name) {
            // SettingsScreen(drawerState)
        }
    }
}

// available routes for the main route
sealed interface NavRoute {
    val name: String
}

data object HomeRoute : NavRoute {
    override val name = "home"
}

data object MealRoute : NavRoute {
    override val name = "meal"
}