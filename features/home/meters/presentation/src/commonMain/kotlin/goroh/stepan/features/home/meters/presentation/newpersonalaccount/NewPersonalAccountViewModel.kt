package goroh.stepan.features.home.meters.presentation.newpersonalaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import goroh.stepan.core.extensions.isEmptyOrBlank
import goroh.stepan.core.flow.mapState
import goroh.stepan.features.home.meters.domain.PersonalAccountsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewPersonalAccountViewModel(
    private val repository: PersonalAccountsRepository
): ViewModel() {
    private val _state = MutableStateFlow(NewPersonalAccountViewModelState())
    val state = _state.mapState { it.toScreenState() }

    fun addPersonalAccount(id: String) {
        if (_state.value.isLoading) {
            return
        }
        if (id.isEmptyOrBlank()) {
            return
        }
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            repository.addPersonalAccount(id).onSuccess {
                _state.update { it.copy(isAdded = true) }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}

private data class NewPersonalAccountViewModelState(
    val isLoading: Boolean = false,
    val isAdded: Boolean = false
) {
    fun toScreenState() = NewPersonalAccountState(
        isLoading = isLoading,
        isAdded = isAdded
    )
}