package goroh.stepan.features.home.meters.data.models

import goroh.stepan.features.home.meters.domain.models.PersonalAccount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonalAccountDto(
    val id: String,
    @SerialName("is_reading_required")
    val isReadingRequired: Boolean,
    @SerialName("payment_amount")
    val paymentAmount: Float
) {
    fun toDomain(): PersonalAccount {
        return PersonalAccount(
            id = id,
            isReadingRequired = isReadingRequired,
            paymentAmount = paymentAmount
        )
    }
}
