package dev.glimmr.starwarssample.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.glimmr.starwarssample.ui.screen.detail.ShipDetailsScreen
import dev.glimmr.starwarssample.ui.screen.home.HomeScreen

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object ShipDetail: Screen("ship_detail/{shipId}"){
        fun routeToShip(shipId: String) = route.replace("{shipId}",shipId)
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route,) {
        composable(route = Screen.Home.route){
            HomeScreen(
                onShipClicked = {shipId ->
                    navController.navigate(Screen.ShipDetail.routeToShip(shipId))
                }
            )
        }
        composable(
            route = Screen.ShipDetail.route,
            arguments = listOf(navArgument("shipId"){ type = NavType.StringType })
        ){
            ShipDetailsScreen(
                onBackButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}