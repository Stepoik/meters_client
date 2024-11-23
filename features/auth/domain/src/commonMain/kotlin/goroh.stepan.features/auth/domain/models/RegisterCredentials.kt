package goroh.stepan.features.auth.domain.models

data class RegisterCredentials(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val firstname: String,
    val lastname: String
) {
    fun toFullCreds(verificationCode: String) = FullRegisterCredentials(
        email = email,
        password = password,
        verificationCode = verificationCode,
        firstname = firstname,
        lastname = lastname
    )
}

data class FullRegisterCredentials(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val verificationCode: String
)
