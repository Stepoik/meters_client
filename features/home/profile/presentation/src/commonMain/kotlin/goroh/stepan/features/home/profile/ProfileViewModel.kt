package goroh.stepan.features.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import goroh.stepan.core.flow.mapState
import goroh.stepan.features.home.profile.domain.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileViewModelState())

    val state = _state.mapState { it.toScreenState() }

    fun loadProfile() {
        if (_state.value.isLoading) return

        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            profileRepository.getProfile().onSuccess { profile ->
                _state.update {
                    it.copy(
                        userData = UserData(
                            firstname = profile.firstname,
                            lastname = profile.lastname
                        )
                    )
                }
            }.onFailure {
                println(it.message)
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}

private data class UserData(
    val firstname: String,
    val lastname: String
)

private data class ProfileViewModelState(
    val userData: UserData? = null,
    val isLoading: Boolean = false
) {
    fun toScreenState(): ProfileState {
        return when {
            isLoading -> ProfileState.Loading
            userData != null -> ProfileState.Success(
                firstname = userData.firstname,
                lastname = userData.lastname
            )

            else -> ProfileState.Idle
        }
    }
}