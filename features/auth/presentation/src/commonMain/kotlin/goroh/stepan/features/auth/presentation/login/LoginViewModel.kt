package goroh.stepan.features.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import goroh.stepan.core.flow.mapState
import goroh.stepan.features.auth.domain.AuthRepository
import goroh.stepan.features.auth.domain.isEmailValid
import goroh.stepan.features.auth.domain.isPasswordValid
import goroh.stepan.features.auth.domain.models.LoginCredentials
import goroh.stepan.features.auth.domain.errors.EmailError
import goroh.stepan.features.auth.domain.errors.IncorrectCredentialsError
import goroh.stepan.features.auth.domain.errors.PasswordError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginViewModelState())
    val state = _state.mapState { it.toScreenState() }

    fun signIn(email: String, password: String) {
        if (_state.value.isLoading || _state.value.isLoggedIn) {
            return
        }
        val loginCredentials = LoginCredentials(email = email, password = password)
        loginCredentials.validate()
            .onFailure { error -> _state.update { it.copy(error = error) } }
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            authRepository.signIn(loginCredentials).onSuccess {
                _state.update { it.copy(isLoggedIn = true) }
            }.onFailure {
                _state.update { it.copy(error = IncorrectCredentialsError()) }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}

private fun LoginCredentials.validate(): Result<Any?> {
    if (!isEmailValid(email)) {
        return Result.failure(EmailError())
    }
    if (!isPasswordValid(password)) {
        return Result.failure(PasswordError())
    }
    return Result.success(Unit)
}

private data class LoginViewModelState(
    val isLoggedIn: Boolean = false,
    val error: Throwable? = null,
    val isLoading: Boolean = false
) {
    fun toScreenState(): LoginScreenState {
        return LoginScreenState(isLoggedIn = isLoggedIn, error = error, isLoading = isLoading)
    }
}