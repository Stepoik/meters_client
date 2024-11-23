package goroh.stepan.core.ktor

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import kotlinx.serialization.json.Json

internal object KtorConfiguration {
    const val connectTimeoutMillis = 15000L
    const val requestTimeoutMillis = 30000L
}

internal val KtorJson = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

internal expect class HttpEngineFactory() {
    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}