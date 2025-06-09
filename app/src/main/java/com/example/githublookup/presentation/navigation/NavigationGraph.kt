package com.example.githublookup.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.githublookup.presentation.screens.MainScreen
import com.example.githublookup.presentation.screens.ProfileScreen
import com.example.githublookup.presentation.screens.SplashScreen

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Splash.route,
        modifier = modifier
    ) {
        composable(Destinations.Splash.route) { SplashScreen(navController) }
        composable(Destinations.Main.route) { MainScreen() }
    }
}
