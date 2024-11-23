package goroh.stepan.features.auth.presentation.register.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import goroh.stepan.core.flow.mapState
import goroh.stepan.features.auth.domain.models.RegisterCredentials
import goroh.stepan.features.auth.domain.usecases.RegisterInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterMainViewModel(
    private val registerInteractor: RegisterInteractor
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterMainViewModelState())
    val state = _state.mapState { it.toScreenState() }

    fun setCredentials(registerCredentials: RegisterCredentials) {
        if (_state.value.isLoading || _state.value.isSent) {
            return
        }
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            registerInteractor.setCredentials(
                credentials = registerCredentials
            ).onSuccess {
                println("HERE")
                _state.update { it.copy(isSent = true) }
            }.onFailure { error ->
                println(error)
                _state.update { it.copy(error = error) }
            }
        }
    }
}

private data class RegisterMainViewModelState(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val isSent: Boolean = false
) {
    fun toScreenState(): RegisterMainScreenState {
        return RegisterMainScreenState(
            isLoading = isLoading,
            error = error,
            isSent = isSent
        )
    }
}