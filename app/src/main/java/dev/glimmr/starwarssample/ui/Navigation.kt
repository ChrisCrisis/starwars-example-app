package dev.glimmr.starwarssample.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.glimmr.starwarssample.ui.screen.home.HomeScreen

sealed class Screen(val route: String) {
    object Home: Screen("home")
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route,) {
        composable(route = Screen.Home.route){
            HomeScreen()
        }
    }
}