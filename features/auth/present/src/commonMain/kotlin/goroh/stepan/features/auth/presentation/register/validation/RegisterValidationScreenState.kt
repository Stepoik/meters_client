package goroh.stepan.features.auth.presentation.register.validation

data class RegisterValidationScreenState(
    val isLoading: Boolean,
    val isLoggedIn: Boolean,
    val error: Throwable?
)
