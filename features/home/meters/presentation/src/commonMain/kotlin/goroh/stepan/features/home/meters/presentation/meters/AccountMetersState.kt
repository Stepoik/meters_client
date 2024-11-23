package goroh.stepan.features.home.meters.presentation.meters

import goroh.stepan.features.home.meters.presentation.meters.models.MetersVO
import goroh.stepan.features.home.meters.presentation.meters.models.PersonalAccountVO

sealed class AccountMetersState {
    data object Loading : AccountMetersState()
    data object Idle : AccountMetersState()
    data class Success(
        val personalAccount: PersonalAccountVO,
        val metersReadings: Map<String, String>,
        val isSending: Boolean,
        val isSent: Boolean
    ) : AccountMetersState()
}