package goroh.stepan.features.home.meters.presentation.newpersonalaccount

import androidx.navigation.NavController

class NewPersonalAccountPresenterImpl(
    private val viewModel: NewPersonalAccountViewModel,
    private val navController: NavController
): NewPersonalAccountPresenter {
    override fun addPersonalAccount(id: String) {
        viewModel.addPersonalAccount(id)
    }

    override fun navigateBack() {
        navController.navigateUp()
    }
}