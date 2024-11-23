package goroh.stepan.features.home.meters.data.models.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddPersonalAccountRequest(
    @SerialName("personal_account")
    val personalAccount: String
)
