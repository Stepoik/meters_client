package goroh.stepan.features.home.meters.presentation.personalaccounts

interface PersonalAccountsPresenter {
    fun loadAccounts()

    fun navigateMeters(personalAccountId: String)

    fun navigateNewPersonalAccount()

    fun pay(personalAccountId: String)
}