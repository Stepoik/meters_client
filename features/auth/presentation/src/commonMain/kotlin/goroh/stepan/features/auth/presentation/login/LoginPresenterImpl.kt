package goroh.stepan.features.auth.presentation.login

import androidx.navigation.NavController
import goroh.stepan.features.auth.presentation.register.navigateRegister
import goroh.stepan.features.common.ApplicationRoute

class LoginPresenterImpl(
    private val viewModel: LoginViewModel,
    private val navController: NavController
): LoginPresenter {
    override fun signIn(email: String, password: String) {
        viewModel.signIn(email = email, password = password)
    }

    override fun navigateRegister() {
        navController.navigateRegister()
    }

    override fun navigateHome() {
        navController.navigate(ApplicationRoute.Home.route)
    }
}