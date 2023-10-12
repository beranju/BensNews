package com.beran.bensnews

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.beran.bensnews.ui.component.BottomBar
import com.beran.bensnews.ui.navigation.Screen
import com.beran.bensnews.ui.screen.ExploreScreen
import com.beran.bensnews.ui.screen.SavedScreen
import com.beran.bensnews.ui.screen.home.HomeScreen
import com.beran.bensnews.ui.screen.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSetup(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(modifier = modifier,
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Explore.route || currentRoute == Screen.Saved.route) {
                BottomBar(navController = navHostController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                val viewModel = koinViewModel<HomeViewModel>()
                HomeScreen(viewModel = viewModel)
            }
            composable(Screen.Explore.route) {
                ExploreScreen()
            }
            composable(Screen.Saved.route) {
                SavedScreen()
            }

        }

    }

}