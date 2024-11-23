package goroh.stepan.features.auth.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import goroh.stepan.features.auth.presentation.login.LoginScreen
import goroh.stepan.features.auth.presentation.register.register
import goroh.stepan.features.common.ApplicationRoute

private val BASE_ROUTE = ApplicationRoute.Auth.route

internal sealed class AuthRoute(val route: String) {
    data object Login : AuthRoute("$BASE_ROUTE/login")
    data object Register : AuthRoute("$BASE_ROUTE/register")
}

fun NavGraphBuilder.auth(navController: NavController) {
    navigation(startDestination = AuthRoute.Login.route, route = ApplicationRoute.Auth.route) {
        composable(AuthRoute.Login.route) {
            LoginScreen(navController)
        }
        register(navController)
    }
}