package com.chumakov123.template.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chumakov123.template.core.presentation.settings.CoreSettingsScreen
import com.chumakov123.template.presentation.main.MainScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = modifier
    ) {
        composable(Screen.Main.route) {
            MainScreen(
                onSettingsClick = {
                    if (navController.currentDestination?.route == Screen.Main.route) {
                        navController.navigate(Screen.Settings.route)
                    }
                }
            )
        }
        composable(Screen.Settings.route) {
            CoreSettingsScreen(
                onBackClick = {
                    if (navController.currentDestination?.route == Screen.Settings.route) {
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}
