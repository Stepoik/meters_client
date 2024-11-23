package goroh.stepan.features.auth.presentation.register.validation

interface RegisterValidationPresenter {
    fun signUp(verificationCode: String)

    fun navigateHome()
}