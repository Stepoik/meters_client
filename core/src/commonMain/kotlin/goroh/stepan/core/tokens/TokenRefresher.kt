package goroh.stepan.core.tokens

interface TokenRefresher {
    suspend fun refresh(refreshToken: String): Tokens?
}