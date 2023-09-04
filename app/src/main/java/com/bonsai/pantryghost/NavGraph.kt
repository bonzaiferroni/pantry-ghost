package com.bonsai.pantryghost

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bonsai.pantryghost.ui.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.mainGraph(drawerState: DrawerState) {
    navigation(startDestination = MainNavOption.HomeScreen.name, route = NavRoutes.MainRoute.name) {
        composable(MainNavOption.HomeScreen.name){
            HomeScreen(drawerState)
        }
        composable(MainNavOption.SettingsScreen.name){
            // SettingsScreen(drawerState)
        }
        composable(MainNavOption.AboutScreen.name){
            // AboutScreen(drawerState)
        }
    }
}

// available routes for the main route
enum class MainNavOption {
    HomeScreen,
    SettingsScreen,
    AboutScreen
}