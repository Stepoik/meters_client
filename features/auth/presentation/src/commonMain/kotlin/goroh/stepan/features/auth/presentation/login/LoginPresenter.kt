package goroh.stepan.features.auth.presentation.login

interface LoginPresenter {
    fun signIn(email: String, password: String)

    fun navigateRegister()

    fun navigateHome()
}