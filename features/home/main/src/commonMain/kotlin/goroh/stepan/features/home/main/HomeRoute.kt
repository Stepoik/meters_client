package goroh.stepan.features.home.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import goroh.stepan.features.common.ApplicationRoute
import goroh.stepan.features.home.meters.presentation.meters
import goroh.stepan.features.home.profile.profile
import goroh.stepan.feautres.home.flow.HomeRoute

fun NavGraphBuilder.home(parentNavController: NavController) {
    composable(ApplicationRoute.Home.route) {
        val navController = rememberNavController()
        Box {
            NavHost(navController = navController, startDestination = HomeRoute.Meters.route) {
                meters(navController)
                profile(navController)
            }
            ApplicationBottomBar(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                navController = navController
            )
        }
    }
}

@Composable
private fun ApplicationBottomBar(modifier: Modifier = Modifier, navController: NavController) {
    val routes = listOf(HomeRoute.Meters, HomeRoute.Profile)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar(modifier = modifier) {
        routes.forEach { screen ->
            NavigationBarItem(selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(screen.icon, contentDescription = null)
                })
        }
    }
}