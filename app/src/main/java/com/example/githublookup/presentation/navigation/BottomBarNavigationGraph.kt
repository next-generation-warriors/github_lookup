package com.example.githublookup.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.githublookup.presentation.screens.BookmarksScreen
import com.example.githublookup.presentation.screens.ProfileScreen
import com.example.githublookup.presentation.screens.SearchScreen

@Composable
fun BottomBarNavigationGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = BottomNavDestinations.Search.route,
        modifier = modifier
    ) {
        composable(BottomNavDestinations.Search.route) {
            SearchScreen(onUserClick = { username ->
                navController.navigate("profile/$username")
            })
        }
        composable(BottomNavDestinations.Bookmarks.route) { BookmarksScreen() }

        composable(Destinations.Profile.route) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: return@composable
            ProfileScreen(userName = username) }
    }
}