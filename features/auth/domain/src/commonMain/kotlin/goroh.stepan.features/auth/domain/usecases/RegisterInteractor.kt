package goroh.stepan.features.auth.domain.usecases

import goroh.stepan.features.auth.domain.AuthRepository
import goroh.stepan.features.auth.domain.errors.EmailError
import goroh.stepan.features.auth.domain.errors.IncorrectCredentialsError
import goroh.stepan.features.auth.domain.errors.PasswordError
import goroh.stepan.features.auth.domain.errors.PasswordNotEqualsError
import goroh.stepan.features.auth.domain.isEmailValid
import goroh.stepan.features.auth.domain.isPasswordValid
import goroh.stepan.features.auth.domain.models.RegisterCredentials
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class RegisterInteractor(
    private val authRepository: AuthRepository
) {
    private val state = MutableStateFlow(RegisterInteractorState())

    suspend fun setCredentials(credentials: RegisterCredentials): Result<Any?> {
        credentials.validate().onFailure { return Result.failure(it) }
        state.update { it.copy(credentials = credentials) }
        return authRepository.sendVerificationCode(email = credentials.email)
    }

    suspend fun signUp(verificationCode: String): Result<Any?> {
        if (verificationCode.isEmpty() || verificationCode.isBlank()) {
            return Result.failure(IncorrectCredentialsError())
        }
        val creds = state.value.credentials?.toFullCreds(verificationCode) ?: return Result.failure(
            IllegalStateException()
        )
        return authRepository.signUp(registerCredentials = creds)
    }
}

private fun RegisterCredentials.validate(): Result<Any?> {
    if (!isEmailValid(email)) {
        return Result.failure(EmailError())
    }
    if (!isPasswordValid(password)) {
        return Result.failure(PasswordError())
    }
    if (password != confirmPassword) {
        return Result.failure(PasswordNotEqualsError())
    }
    return Result.success(Unit)
}

private data class RegisterInteractorState(
    val credentials: RegisterCredentials? = null
)