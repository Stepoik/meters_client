package goroh.stepan.core.tokens

import goroh.stepan.core.net.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal class TokenRefresherImpl(
    private val httpClient: HttpClient
) : TokenRefresher {
    override suspend fun refresh(refreshToken: String): Tokens? {
        return runCatching {
            httpClient.post("$BASE_URL/auth/refresh") {
                contentType(ContentType.Application.Json)
                setBody(RefreshRequest(refreshToken))
            }.body<TokensResponse>()
        }.getOrNull()?.toTokens()
    }
}

@Serializable
private data class RefreshRequest(
    @SerialName("refresh_token")
    val refreshToken: String
)