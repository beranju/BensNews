package com.beran.bensnews.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.beran.bensnews.R
import com.beran.bensnews.ui.navigation.NavigationItem
import com.beran.bensnews.ui.navigation.Screen
import com.beran.bensnews.ui.theme.BensNewsTheme

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(modifier = modifier) {
        val navigationItems = listOf(
            NavigationItem(
                stringResource(R.string.home),
                Icons.Default.Home,
                Screen.Home
            ),
            NavigationItem(
                stringResource(R.string.explore),
                Icons.Default.TravelExplore,
                Screen.Explore
            ),
            NavigationItem(
                stringResource(R.string.saved),
                Icons.Default.Bookmark,
                Screen.Saved
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                }, label = { Text(text = item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPrev() {
    BensNewsTheme {
        BottomBar(navController = rememberNavController())
    }
}