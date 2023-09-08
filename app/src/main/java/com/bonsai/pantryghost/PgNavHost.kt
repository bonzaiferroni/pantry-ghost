package com.bonsai.pantryghost

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bonsai.pantryghost.ui.food.EditFoodScreen
import com.bonsai.pantryghost.ui.food.FoodScreen
import com.bonsai.pantryghost.ui.home.HomeScreen

@Composable
fun PgNavHost(
    navController: NavHostController,
    drawerState: DrawerState,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.FoodRoute.name
    ) {
        composable(route = NavRoute.HomeRoute.name) {
            HomeScreen(drawerState = drawerState)
        }
        composable(route = NavRoute.FoodRoute.name) {
            FoodScreen(drawerState = drawerState, navController = navController)
        }
        composable(
            route = NavRoute.EditFoodRoute.routeWithArgs,
            arguments = NavRoute.EditFoodRoute.argList
        ) {
            EditFoodScreen(drawerState = drawerState, navController = navController)
        }
    }
}

// args


// routes
sealed interface NavRoute {
    val name: String

    data object HomeRoute : NavRoute {
        override val name = "home"
    }

    data object FoodRoute : NavRoute {
        override val name = "food"
    }

    data object EditFoodRoute : NavRoute {
        override val name = "edit_food"
        val routeWithArgs = "${name}/{$idArg}"
        val argList = listOf(idNavArg)
    }

    data object MealRoute : NavRoute {
        override val name = "meal"
    }

    data object EditMeal : NavRoute {
        override val name = "edit_meal"
        val routeWithArgs = "${EditFoodRoute.name}/{$idArg}"
        val argList = listOf(idNavArg)
    }

    companion object {
        val idArg = "id"
        val idNavArg = navArgument(idArg) { type = NavType.IntType }
    }
}

// extensions
fun NavHostController.navigateRoute(navRoute: NavRoute, idArg: Int? = null) {
    val route = if (idArg != null) "${navRoute.name}/$idArg" else navRoute.name
    this.navigate(route)
}