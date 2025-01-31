package com.application.drinkdare.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.application.drinkdare.ui.screen.signin.LoginScreen
import com.application.drinkdare.ui.screen.register.RegisterScreen
import com.application.drinkdare.ui.screen.carta.CartaScreen // Suponiendo que tienes una pantalla de inicio

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Carta : Screen("carta")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }

        composable(Screen.Carta.route) {
            CartaScreen()  // Asegúrate de tener una pantalla principal de tu aplicación
        }
    }
}
