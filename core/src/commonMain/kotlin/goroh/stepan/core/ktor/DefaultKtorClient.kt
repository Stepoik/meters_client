package goroh.stepan.core.ktor

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

const val DEFAULT_KTOR_CLIENT = "default_ktor_client"

fun defaultKtorClient(): HttpClient {
    val client = HttpClient(HttpEngineFactory().createEngine()) {
        install(ContentNegotiation) {
            json(KtorJson)
        }

        install(HttpTimeout) {
            connectTimeoutMillis = KtorConfiguration.connectTimeoutMillis
            requestTimeoutMillis = KtorConfiguration.requestTimeoutMillis
        }
    }
    return client
}