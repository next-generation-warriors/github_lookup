package com.example.githublookup.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.githublookup.presentation.components.BottomNavigationBar
import com.example.githublookup.presentation.components.TopBar
import com.example.githublookup.presentation.navigation.BottomBarNavigationGraph
import com.example.githublookup.presentation.navigation.BottomNavDestinations
import com.example.githublookup.ui.theme.GithubLookupTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomBarRoutes = listOf(
        BottomNavDestinations.Search.route,
        BottomNavDestinations.Bookmarks.route
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    val showBottomBar = currentDestination in bottomBarRoutes

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController)
            }
        }) { paddingValues ->

        BottomBarNavigationGraph(navController, Modifier.padding(paddingValues))
    }
}

@Preview
@Composable
fun ShowMainScreen() {
    GithubLookupTheme {
        MainScreen()
    }
}