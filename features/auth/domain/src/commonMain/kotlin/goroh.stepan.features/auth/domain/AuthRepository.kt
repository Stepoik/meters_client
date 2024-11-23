package goroh.stepan.features.auth.domain

import goroh.stepan.features.auth.domain.models.FullRegisterCredentials
import goroh.stepan.features.auth.domain.models.LoginCredentials

interface AuthRepository {
    suspend fun signIn(loginCredentials: LoginCredentials): Result<Any?>

    suspend fun signUp(registerCredentials: FullRegisterCredentials): Result<Any?>

    suspend fun sendVerificationCode(email: String): Result<Any?>
}