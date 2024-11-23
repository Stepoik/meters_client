package goroh.stepan.features.auth.presentation.register.validation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import goroh.stepan.core.flow.mapState
import goroh.stepan.features.auth.domain.usecases.RegisterInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterValidationViewModel(
    private val registerInteractor: RegisterInteractor
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterValidationViewModelState())
    val state = _state.mapState { it.toScreenState() }

    fun signUp(verificationCode: String) {
        if (_state.value.isLoading || _state.value.isLoggedIn) {
            return
        }
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            registerInteractor.signUp(verificationCode).onSuccess {
                _state.update { it.copy(isLoggedIn = true) }
            }.onFailure { error ->
                _state.update { it.copy(error = error) }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}

private data class RegisterValidationViewModelState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: Throwable? = null
) {
    fun toScreenState(): RegisterValidationScreenState {
        return RegisterValidationScreenState(
            isLoading = isLoading,
            isLoggedIn = isLoggedIn,
            error = error
        )
    }
}