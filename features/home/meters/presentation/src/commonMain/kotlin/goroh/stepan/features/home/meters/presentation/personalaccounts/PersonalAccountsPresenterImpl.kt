package goroh.stepan.features.home.meters.presentation.personalaccounts

import androidx.navigation.NavController
import goroh.stepan.features.home.meters.presentation.MetersRoute
import goroh.stepan.features.home.meters.presentation.meters.navigateAccountMeters

class PersonalAccountsPresenterImpl(
    private val viewModel: PersonalAccountsViewModel,
    private val navController: NavController
) : PersonalAccountsPresenter {
    override fun loadAccounts() {
        viewModel.loadAccounts()
    }

    override fun navigateMeters(personalAccountId: String) {
        navController.navigateAccountMeters(id = personalAccountId)
    }

    override fun navigateNewPersonalAccount() {
        navController.navigate(MetersRoute.NewPersonalAccount.route)
    }

    override fun pay(personalAccountId: String) {
        viewModel.payForMeters(personalAccountId)
    }
}