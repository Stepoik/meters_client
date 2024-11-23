package goroh.stepan.core.tokens

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokensResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String
)

fun TokensResponse.toTokens() = Tokens(
    accessToken = accessToken,
    refreshToken = refreshToken
)