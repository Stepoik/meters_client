package goroh.stepan.features.auth.data.models

import goroh.stepan.features.auth.domain.models.LoginCredentials
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginCredentialsDto(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
) {
    constructor(loginCredentials: LoginCredentials) : this(
        email = loginCredentials.email,
        password = loginCredentials.password
    )
}
