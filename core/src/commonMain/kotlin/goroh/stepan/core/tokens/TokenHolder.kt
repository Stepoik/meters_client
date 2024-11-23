package goroh.stepan.core.tokens

interface TokenHolder {
    suspend fun saveTokens(tokens: Tokens?)

    suspend fun getTokens(): Tokens?
}