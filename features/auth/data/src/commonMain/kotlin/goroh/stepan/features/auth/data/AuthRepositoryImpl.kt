package goroh.stepan.features.auth.data

import goroh.stepan.core.tokens.TokenHolder
import goroh.stepan.core.tokens.toTokens
import goroh.stepan.features.auth.domain.AuthRepository
import goroh.stepan.features.auth.domain.models.FullRegisterCredentials
import goroh.stepan.features.auth.domain.models.LoginCredentials

class AuthRepositoryImpl(
    private val tokenHolder: TokenHolder,
    private val authKtorDatastore: AuthKtorDatastore
) : AuthRepository {
    override suspend fun signIn(loginCredentials: LoginCredentials): Result<Any?> {
        val tokens = authKtorDatastore.signIn(loginCredentials).getOrElse { return Result.failure(it) }
        tokenHolder.saveTokens(tokens.toTokens())
        return Result.success(Unit)
    }

    override suspend fun signUp(registerCredentials: FullRegisterCredentials): Result<Any?> {
        val tokens = authKtorDatastore.signUp(registerCredentials).getOrElse { return Result.failure(it) }
        tokenHolder.saveTokens(tokens.toTokens())
        return Result.success(Unit)    }

    override suspend fun sendVerificationCode(email: String): Result<Any?> {
        return authKtorDatastore.sendVerificationCode(email)
    }
}