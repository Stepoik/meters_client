package goroh.stepan.core.ktor

import goroh.stepan.core.tokens.TokenHolder
import goroh.stepan.core.tokens.TokenRefresher
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.plugin
import io.ktor.client.request.headers
import io.ktor.http.HttpStatusCode
import io.ktor.http.auth.AuthScheme
import io.ktor.serialization.kotlinx.json.json

const val AUTHORIZED_KTOR_CLIENT = "authorized_ktor_client"

fun authorizedKtorClient(tokenHolder: TokenHolder, refresher: TokenRefresher): HttpClient {
    val client = HttpClient(HttpEngineFactory().createEngine()) {
        install(ContentNegotiation) {
            json(KtorJson)
        }

        install(HttpTimeout) {
            connectTimeoutMillis = KtorConfiguration.connectTimeoutMillis
            requestTimeoutMillis = KtorConfiguration.requestTimeoutMillis
        }
    }
    authInterceptor(client = client, tokenHolder = tokenHolder, refresher = refresher)
    return client
}

private fun authInterceptor(client: HttpClient, tokenHolder: TokenHolder, refresher: TokenRefresher) {
    client.plugin(HttpSend).intercept { request ->
        val tokens = tokenHolder.getTokens()
        var access = tokens?.accessToken ?: throw IllegalStateException("Access token is missing. User may need to log in again.")
        val refresh = tokens.refreshToken
        // Обычный запрос
        request.headers {
            append("Authorization", "${AuthScheme.Bearer} $access")
        }
        val originalCall = execute(request)
        if (originalCall.response.status == HttpStatusCode.Unauthorized) {
            // Повторная попытка с авторизацией
            val newTokens = refresher.refresh(refresh)
            tokenHolder.saveTokens(newTokens)
            access = newTokens?.accessToken!!
            request.headers {
                set("Authorization", "${AuthScheme.Bearer} $access")
            }
            execute(request)
        } else {
            originalCall
        }
    }
}