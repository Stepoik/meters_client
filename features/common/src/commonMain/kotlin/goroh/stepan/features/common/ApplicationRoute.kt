package goroh.stepan.features.common

sealed class ApplicationRoute(val route: String) {
    data object Auth: ApplicationRoute("auth")
    data object Home: ApplicationRoute("home")
    data object Splash: ApplicationRoute("splash")
}