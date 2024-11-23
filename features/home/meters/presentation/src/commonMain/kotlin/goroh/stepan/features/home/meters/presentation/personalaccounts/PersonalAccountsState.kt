package goroh.stepan.features.home.meters.presentation.personalaccounts

import goroh.stepan.features.home.meters.presentation.personalaccounts.models.PersonalAccountVO

sealed class PersonalAccountsState {
    data object Loading : PersonalAccountsState()

    data class Success(
        val accounts: List<PersonalAccountVO>
    ) : PersonalAccountsState()

    data object Idle : PersonalAccountsState()

    data class Error(val message: String) : PersonalAccountsState()
}