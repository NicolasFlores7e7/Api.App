package com.example.apilistapp.navigation

sealed class Routes(val route: String){
    object List:Routes("list")
    object Detailed:Routes("detailed")
    object Favourites:Routes("fav")
}