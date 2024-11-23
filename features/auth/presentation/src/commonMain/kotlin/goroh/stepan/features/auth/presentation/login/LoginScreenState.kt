package goroh.stepan.features.auth.presentation.login

data class LoginScreenState(
    val isLoggedIn: Boolean,
    val error: Throwable?,
    val isLoading: Boolean
)