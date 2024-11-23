package goroh.stepan.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import goroh.stepan.core.flow.mapState
import goroh.stepan.core.tokens.TokenHolder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val tokenHolder: TokenHolder
) : ViewModel() {
    private val _state = MutableStateFlow(SplashViewModelState())
    val state = _state.mapState { it.toScreenState() }

    fun check() {
        if (_state.value.isChecking) {
            return
        }
        _state.update { it.copy(isChecking = true) }
        viewModelScope.launch {
            val tokens = tokenHolder.getTokens()
            _state.update { it.copy(isAuthorized = tokens != null, isChecked = true, isChecking = false) }
        }
    }
}

private data class SplashViewModelState(
    val isChecking: Boolean = false,
    val isChecked: Boolean = false,
    val isAuthorized: Boolean = false
) {
    fun toScreenState(): SplashScreenState {
        return when {
            !isChecked -> SplashScreenState.Checking
            else -> {
                if (isAuthorized) SplashScreenState.Authorized else SplashScreenState.Unauthorized
            }
        }
    }
}