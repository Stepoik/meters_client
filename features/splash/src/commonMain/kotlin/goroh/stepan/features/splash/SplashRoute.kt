package goroh.stepan.features.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import goroh.stepan.features.common.ApplicationRoute

fun NavGraphBuilder.splash(navController: NavController) {
    composable(ApplicationRoute.Splash.route) {
        SplashScreen(navController)
    }
}