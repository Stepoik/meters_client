package goroh.stepan.features.splash

sealed class SplashScreenState {
    data object Checking : SplashScreenState()
    data object Authorized : SplashScreenState()
    data object Unauthorized : SplashScreenState()
}