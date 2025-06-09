package com.example.githublookup.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(val route: String) {

    object Splash: Destinations("splash",  )
    object Main : Destinations("main")
    object Profile: Destinations("profile/{username}")
}