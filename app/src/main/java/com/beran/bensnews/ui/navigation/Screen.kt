package com.beran.bensnews.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Saved : Screen("saved")
    object Search: Screen("search")
    object Detail: Screen("detail")
}