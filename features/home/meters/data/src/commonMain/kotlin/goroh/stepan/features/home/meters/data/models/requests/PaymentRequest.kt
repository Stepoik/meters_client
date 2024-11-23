package goroh.stepan.features.home.meters.data.models.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentRequest(
    @SerialName("personal_account_id")
    val personalAccountId: String
)
