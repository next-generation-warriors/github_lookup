package com.example.githublookup.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavDestinations(val route: String, val icon: ImageVector, val label: String) {

    object Search : BottomNavDestinations("search", Icons.Default.Search, "Search")
    object Bookmarks: BottomNavDestinations("bookmarks", Icons.Default.Bookmarks, "Bookmarks")
}