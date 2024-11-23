package goroh.stepan.features.auth.presentation.register

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import goroh.stepan.features.auth.presentation.AuthRoute
import goroh.stepan.features.auth.presentation.register.main.RegisterMainScreen
import goroh.stepan.features.auth.presentation.register.validation.RegisterValidationScreen
import org.koin.compose.viewmodel.koinViewModel

private val BASE_ROUTE = AuthRoute.Register.route

internal sealed class RegisterRoute(val route: String) {
    data object Main : RegisterRoute("$BASE_ROUTE/main")
    data object Validation : RegisterRoute("$BASE_ROUTE/validation")
}

fun NavGraphBuilder.register(parentNavController: NavController) {
    composable(route = BASE_ROUTE) {
        val navController = rememberNavController()
        val registerSharedViewModel = koinViewModel<RegisterSharedViewModel>()
        NavHost(navController, startDestination = RegisterRoute.Main.route) {
            composable(RegisterRoute.Main.route) {
                RegisterMainScreen(
                    navController = navController,
                    registerInteractor = registerSharedViewModel.registerInteractor
                )
            }
            composable(RegisterRoute.Validation.route) {
                RegisterValidationScreen(
                    parentNavController = parentNavController,
                    registerInteractor = registerSharedViewModel.registerInteractor
                )
            }
        }
    }
}

fun NavController.navigateRegister() {
    navigate(BASE_ROUTE)
}