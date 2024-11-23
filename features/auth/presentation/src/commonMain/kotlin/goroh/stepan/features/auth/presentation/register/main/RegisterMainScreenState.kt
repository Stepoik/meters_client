package goroh.stepan.features.auth.presentation.register.main

data class RegisterMainScreenState(
    val error: Throwable?,
    val isSent: Boolean,
    val isLoading: Boolean
)
