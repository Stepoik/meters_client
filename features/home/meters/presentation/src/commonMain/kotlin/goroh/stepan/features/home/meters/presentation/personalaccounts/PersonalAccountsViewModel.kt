package goroh.stepan.features.home.meters.presentation.personalaccounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import goroh.stepan.core.flow.mapState
import goroh.stepan.features.home.meters.domain.PersonalAccountsRepository
import goroh.stepan.features.home.meters.presentation.personalaccounts.models.PersonalAccountVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PersonalAccountsViewModel(
    private val personalAccountsRepository: PersonalAccountsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(PersonalAccountsViewModelState())

    val state = _state.mapState { it.toScreenState() }

    fun loadAccounts() {
        if (_state.value.isLoading) {
            return
        }
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            personalAccountsRepository.getPersonalAccounts().onSuccess { accounts ->
                _state.update { it.copy(accounts = accounts.map(::PersonalAccountVO)) }
            }.onFailure { error ->
                _state.update { it.copy(error = error) }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun payForMeters(personalAccount: String) {
        viewModelScope.launch {
            personalAccountsRepository.payForMeters(personalAccount)
            loadAccounts()
        }
    }
}

private data class PersonalAccountsViewModelState(
    val isLoading: Boolean = false,
    val accounts: List<PersonalAccountVO> = listOf(),
    val error: Throwable? = null
) {
    fun toScreenState(): PersonalAccountsState {
        return when {
            isLoading -> PersonalAccountsState.Loading
            error != null -> PersonalAccountsState.Error(error.message ?: "")
            else -> PersonalAccountsState.Success(accounts = accounts)
        }
    }
}