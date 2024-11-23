package goroh.stepan.features.home.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import goroh.stepan.feautres.home.flow.HomeRoute

fun NavGraphBuilder.profile(navController: NavController) {
    composable(HomeRoute.Profile.route) {
        ProfileScreen(navController)
    }
}