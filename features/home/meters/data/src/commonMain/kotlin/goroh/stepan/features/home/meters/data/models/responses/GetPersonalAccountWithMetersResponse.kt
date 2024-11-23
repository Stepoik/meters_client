package goroh.stepan.features.home.meters.data.models.responses

import goroh.stepan.features.home.meters.data.models.PersonalAccountDto
import goroh.stepan.features.home.meters.domain.models.PersonalAccountWithMeters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPersonalAccountWithMetersResponse(
    @SerialName("personal_account")
    val personalAccount: PersonalAccountDto,
    @SerialName("meters")
    val meters: List<MeterDto>
) {
    fun toDomain() = PersonalAccountWithMeters(
        personalAccount = personalAccount.toDomain(),
        meters = meters.map { it.toDomain() }
    )
}
