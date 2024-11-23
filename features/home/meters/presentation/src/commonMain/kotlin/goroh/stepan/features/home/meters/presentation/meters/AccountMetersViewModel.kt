package goroh.stepan.features.home.meters.presentation.meters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import goroh.stepan.core.extensions.isEmptyOrBlank
import goroh.stepan.core.flow.mapState
import goroh.stepan.features.home.meters.domain.PersonalAccountsRepository
import goroh.stepan.features.home.meters.domain.models.MeterReading
import goroh.stepan.features.home.meters.domain.models.PersonalAccountReading
import goroh.stepan.features.home.meters.presentation.meters.models.PersonalAccountVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountMetersViewModel(
    private val personAccountId: String,
    private val personalAccountsRepository: PersonalAccountsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(AccountMetersViewModelState())
    val state = _state.mapState { it.toScreenState() }

    fun loadMeters() {
        if (_state.value.isLoading) {
            return
        }
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            personalAccountsRepository.getPersonalAccountWithMeters(personAccountId)
                .onSuccess { personalAccount ->
                    _state.update { it.copy(personalAccount = PersonalAccountVO(personalAccount)) }
                }
            _state.update { it.copy(isLoading = false) }
        }
    }

    fun setMetersValue(id: String, value: String) {
        val currentValues = _state.value.metersReadings.toMutableMap()
        currentValues[id] = value
        _state.update { it.copy(metersReadings = currentValues) }
    }

    fun sendMeters() {
        val state = _state.value
        if (state.isSending) {
            return
        }
        val meters = state.personalAccount?.meters ?: return
        _state.update { it.copy(isSending = true) }
        viewModelScope.launch {
            personalAccountsRepository.sendMetersReadings(personalAccountReading = PersonalAccountReading(
                metersReadings = meters.map {
                    val metersValue =
                        state.metersReadings[it.title]?.apply {
                            if (isEmptyOrBlank()) {
                                _state.update {
                                    it.copy(
                                        isSending = false
                                    )
                                }
                                return@launch
                            }
                        } ?: run {
                            _state.update {
                                it.copy(
                                    isSending = false
                                )
                            }
                            return@launch
                        }
                    MeterReading(
                        id = it.title,
                        value = metersValue.toFloatOrNull() ?: 0f
                    )
                }
            )).onSuccess {
                _state.update { it.copy(isSent = true) }
            }.onFailure {
                println(it)
            }
            _state.update { it.copy(isSending = false) }
        }
    }
}

private data class AccountMetersViewModelState(
    val isLoading: Boolean = false,
    val personalAccount: PersonalAccountVO? = null,
    val metersReadings: Map<String, String> = mapOf(),
    val isSending: Boolean = false,
    val isSent: Boolean = false
) {
    fun toScreenState(): AccountMetersState {
        return when {
            isLoading -> AccountMetersState.Loading
            personalAccount != null -> AccountMetersState.Success(
                personalAccount = personalAccount,
                metersReadings = metersReadings,
                isSent = isSent,
                isSending = isSending
            )

            else -> AccountMetersState.Idle
        }
    }
}