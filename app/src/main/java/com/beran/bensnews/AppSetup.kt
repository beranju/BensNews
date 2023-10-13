package com.beran.bensnews

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.beran.bensnews.ui.component.BottomBar
import com.beran.bensnews.ui.navigation.Screen
import com.beran.bensnews.ui.screen.detail.DetailScreen
import com.beran.bensnews.ui.screen.detail.DetailViewModel
import com.beran.bensnews.ui.screen.explore.ExploreScreen
import com.beran.bensnews.ui.screen.explore.ExploreViewModel
import com.beran.bensnews.ui.screen.explore.search.SearchScreen
import com.beran.bensnews.ui.screen.explore.search.SearchViewModel
import com.beran.bensnews.ui.screen.home.HomeScreen
import com.beran.bensnews.ui.screen.home.HomeViewModel
import com.beran.bensnews.ui.screen.saved.SavedScreen
import com.beran.bensnews.ui.screen.saved.SavedViewModel
import com.beran.bensnews.utils.ExtensionFun.navigate
import com.beran.core.domain.model.NewsModel
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
                HomeScreen(viewModel = viewModel, navigateToDetail = { news ->
                    navHostController.navigate(Screen.Detail.route, bundleOf("data" to news))
                })
            }
            composable(Screen.Explore.route) {
                val viewModel = koinViewModel<ExploreViewModel>()
                ExploreScreen(viewModel = viewModel, navigateToSearch = {
                    navHostController.navigate(Screen.Search.route)
                },
                    navigateToDetail = { news ->
                        navHostController.navigate(Screen.Detail.route, bundleOf("data" to news))
                    })
            }
            composable(Screen.Saved.route) {
                val viewModel = koinViewModel<SavedViewModel>()
                SavedScreen(
                    viewModel = viewModel,
                    navigateToDetail = {news ->
                        navHostController.navigate(Screen.Detail.route, bundleOf("data" to news))
                    }
                )
            }
            composable(Screen.Search.route) {
                val viewModel = koinViewModel<SearchViewModel>()
                SearchScreen(viewModel = viewModel, navigateToDetail = { news ->
                    navHostController.navigate(Screen.Detail.route, bundleOf("data" to news))
                })
            }
            composable(Screen.Detail.route) {
                val data = it.arguments?.getParcelable<NewsModel>("data")
                val viewModel = koinViewModel<DetailViewModel>()
                DetailScreen(data = data!!, detailViewModel = viewModel, navigateBack = {
                    navHostController.navigateUp()
                })
            }
        }
    }
}