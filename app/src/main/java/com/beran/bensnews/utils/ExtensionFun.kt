package com.beran.bensnews.utils

import android.os.Bundle
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

object ExtensionFun {
    /**
     * extension fun for navController to pass custom data
     * ref => https://stackoverflow.com/a/68847035/18219793
     */
    fun NavController.navigate(
        route: String,
        args: Bundle,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        val routeLink = NavDeepLinkRequest
            .Builder
            .fromUri(NavDestination.createRoute(route).toUri())
            .build()

        val deepLinkMatch = graph.matchDeepLink(routeLink)
        if (deepLinkMatch != null) {
            val destination = deepLinkMatch.destination
            val id = destination.id
            navigate(id, args, navOptions, navigatorExtras)
        } else {
            navigate(route, navOptions, navigatorExtras)
        }
    }
}