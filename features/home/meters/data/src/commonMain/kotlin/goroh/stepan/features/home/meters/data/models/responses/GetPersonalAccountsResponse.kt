package goroh.stepan.features.home.meters.data.models.responses

import goroh.stepan.features.home.meters.data.models.PersonalAccountDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPersonalAccountsResponse(
    @SerialName("personal_accounts")
    val personalAccounts: List<PersonalAccountDto>
)
