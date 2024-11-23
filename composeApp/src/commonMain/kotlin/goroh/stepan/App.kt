package goroh.stepan

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import goroh.stepan.features.auth.presentation.auth
import goroh.stepan.features.common.ApplicationRoute
import goroh.stepan.features.home.main.home
import goroh.stepan.features.splash.splash
import goroh.stepan.uikit.MetersTheme

@Composable
fun App() {
    MetersTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = ApplicationRoute.Splash.route) {
            auth(navController)
            home(parentNavController = navController)
            splash(navController)
        }
    }
}