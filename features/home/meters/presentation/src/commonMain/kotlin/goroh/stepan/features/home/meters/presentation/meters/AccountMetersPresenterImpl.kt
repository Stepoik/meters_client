package goroh.stepan.features.home.meters.presentation.meters

import androidx.navigation.NavController

class AccountMetersPresenterImpl(
    private val navController: NavController,
    private val viewModel: AccountMetersViewModel
): AccountMetersPresenter {
    override fun loadMeters() {
        viewModel.loadMeters()
    }

    override fun setMeterReading(id: String, value: String) {
        viewModel.setMetersValue(id = id, value = value)
    }

    override fun sendMeters() {
        viewModel.sendMeters()
    }

    override fun navigateBack() {
        navController.navigateUp()
    }
}