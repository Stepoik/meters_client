package goroh.stepan.features.auth.presentation.register.validation

import androidx.navigation.NavController
import goroh.stepan.features.common.ApplicationRoute

class RegisterValidationPresenterImpl(
    private val viewModel: RegisterValidationViewModel,
    private val parentNavController: NavController
) : RegisterValidationPresenter {
    override fun signUp(verificationCode: String) {
        viewModel.signUp(verificationCode)
    }

    override fun navigateHome() {
        parentNavController.navigate(ApplicationRoute.Home.route)
    }

}