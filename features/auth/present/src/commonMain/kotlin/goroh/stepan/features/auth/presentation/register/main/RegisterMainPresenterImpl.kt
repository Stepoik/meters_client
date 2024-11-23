package goroh.stepan.features.auth.presentation.register.main

import androidx.navigation.NavController
import goroh.stepan.features.auth.domain.models.RegisterCredentials
import goroh.stepan.features.auth.presentation.register.RegisterRoute

class RegisterMainPresenterImpl(
    private val viewModel: RegisterMainViewModel,
    private val navController: NavController
) : RegisterMainPresenter {
    override fun setCredentials(registerCredentials: RegisterCredentials) {
        viewModel.setCredentials(registerCredentials)
    }

    override fun navigateValidation() {
        navController.navigate(RegisterRoute.Validation.route)
    }
}