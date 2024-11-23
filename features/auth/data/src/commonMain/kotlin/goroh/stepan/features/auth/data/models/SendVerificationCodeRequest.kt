package goroh.stepan.features.auth.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendVerificationCodeRequest(
    @SerialName("email")
    val email: String
)
