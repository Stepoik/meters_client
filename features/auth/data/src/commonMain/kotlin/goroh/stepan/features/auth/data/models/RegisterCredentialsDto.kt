package goroh.stepan.features.auth.data.models

import goroh.stepan.features.auth.domain.models.FullRegisterCredentials
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterCredentialsDto(
    @SerialName("firstname")
    val firstname: String,
    @SerialName("lastname")
    val lastname: String,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("verification_code")
    val verificationCode: String
) {
    constructor(registerCredentials: FullRegisterCredentials) : this(
        firstname = registerCredentials.firstname,
        lastname = registerCredentials.lastname,
        email = registerCredentials.email,
        password = registerCredentials.password,
        verificationCode = registerCredentials.verificationCode
    )
}
