package com.application.drinkdare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.application.drinkdare.ui.screen.carta.CartaScreen

sealed class Screen(val route: String) {
    object Carta : Screen("Carta")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Carta.route) {
        composable(Screen.Carta.route) {
            CartaScreen()
        }
    }
}