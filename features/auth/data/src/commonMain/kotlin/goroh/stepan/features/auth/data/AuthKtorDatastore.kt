package goroh.stepan.features.auth.data

import goroh.stepan.core.net.BASE_URL
import goroh.stepan.core.tokens.TokensResponse
import goroh.stepan.features.auth.data.models.LoginCredentialsDto
import goroh.stepan.features.auth.data.models.RegisterCredentialsDto
import goroh.stepan.features.auth.data.models.SendVerificationCodeRequest
import goroh.stepan.features.auth.domain.models.FullRegisterCredentials
import goroh.stepan.features.auth.domain.models.LoginCredentials
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class AuthKtorDatastore(
    private val httpClient: HttpClient
) {
    suspend fun signIn(loginCredentials: LoginCredentials): Result<TokensResponse> {
        return runCatching {
            val response = httpClient.post("$BASE_URL/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginCredentialsDto(loginCredentials))
            }
            response.body<TokensResponse>()
        }
    }

    suspend fun signUp(registerCredentials: FullRegisterCredentials): Result<TokensResponse> {
        return runCatching {
            httpClient.post("$BASE_URL/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(RegisterCredentialsDto(registerCredentials))
            }.body()
        }
    }

    suspend fun sendVerificationCode(email: String): Result<Any?> {
        return runCatching {
            val response = httpClient.post("$BASE_URL/auth/send_verification_code") {
                contentType(ContentType.Application.Json)
                setBody(SendVerificationCodeRequest(email))
            }
            check(response.status == HttpStatusCode.OK)
        }
    }
}