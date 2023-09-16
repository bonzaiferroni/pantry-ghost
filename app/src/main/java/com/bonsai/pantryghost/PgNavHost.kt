package com.bonsai.pantryghost

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bonsai.pantryghost.ui.day.DayScreen
import com.bonsai.pantryghost.ui.food.EditFoodScreen
import com.bonsai.pantryghost.ui.food.FoodScreen
import com.bonsai.pantryghost.ui.food.ScanFoodScreen
import com.bonsai.pantryghost.ui.home.HomeScreen
import java.time.LocalDate

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
        composable(
            route = NavRoute.DayRoute.routeWithArgs,
            arguments = NavRoute.DayRoute.argList
        ) {
            DayScreen(drawerState = drawerState, navController = navController)
        }
        composable(route = NavRoute.ScanFoodRoute.name) {
            ScanFoodScreen(drawerState = drawerState)
        }
    }
}

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

    data object DayRoute : NavRoute {
        override val name = "day"
        val routeWithArgs = "${name}/{$dateArg}"
        val argList = listOf(dateNavArg)
    }

    data object ScanFoodRoute : NavRoute {
        override val name = "scan_food"
    }

    companion object {
        const val dateArg = "date"
        val dateNavArg = navArgument(dateArg) {
            type = NavType.StringType; defaultValue = LocalDate.now().toString()
        }

        const val idArg = "id"
        val idNavArg = navArgument(idArg) { type = NavType.IntType }
    }
}

// extensions
fun NavHostController.navigateRoute(navRoute: NavRoute, idArg: Int? = null) {
    val route = if (idArg != null) "${navRoute.name}/$idArg" else navRoute.name
    this.navigate(route)
}

fun NavHostController.navigateRoute(navRoute: NavRoute, localDate: LocalDate) {
    val route = "${navRoute.name}/$localDate"
    this.navigate(route)
}

fun SavedStateHandle.getLocalDate(): LocalDate {
    val dateString: String = checkNotNull(this[NavRoute.dateArg])
    return LocalDate.parse(dateString)
}

fun SavedStateHandle.getId(): Int {
    return checkNotNull(this.get<Int>(NavRoute.idArg))
}