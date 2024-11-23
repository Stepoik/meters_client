package goroh.stepan.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import goroh.stepan.features.common.ApplicationRoute
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = koinViewModel<SplashViewModel>()
    val state = viewModel.state.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.check()
    }
    LaunchedEffect(state) {
        when (state) {
            is SplashScreenState.Authorized -> navController.navigate(ApplicationRoute.Home.route)
            is SplashScreenState.Unauthorized -> navController.navigate(ApplicationRoute.Auth.route)
            else -> {}
        }
    }
}