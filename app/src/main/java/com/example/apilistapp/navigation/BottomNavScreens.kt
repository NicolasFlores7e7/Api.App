package com.example.apilistapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreens (val route: String, val icon: ImageVector){
    object Home:BottomNavScreens(Routes.List.route,Icons.Filled.Home )
    object Favorite:BottomNavScreens(Routes.Favourites.route,Icons.Filled.Favorite)

}