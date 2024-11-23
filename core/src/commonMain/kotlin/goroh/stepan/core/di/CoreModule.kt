package goroh.stepan.core.di

import goroh.stepan.core.ktor.AUTHORIZED_KTOR_CLIENT
import goroh.stepan.core.ktor.DEFAULT_KTOR_CLIENT
import goroh.stepan.core.ktor.authorizedKtorClient
import goroh.stepan.core.ktor.defaultKtorClient
import goroh.stepan.core.tokens.TokenHolder
import goroh.stepan.core.tokens.TokenHolderImpl
import goroh.stepan.core.tokens.TokenRefresher
import goroh.stepan.core.tokens.TokenRefresherImpl
import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

internal expect fun platformCoreModule(): Module

val AUTHORIZED_KTOR_CLIENT_QUALIFIER = qualifier(AUTHORIZED_KTOR_CLIENT)
val DEFAULT_KTOR_CLIENT_QUALIFIER = qualifier(DEFAULT_KTOR_CLIENT)

val coreModule = module {
    includes(platformCoreModule())
    single(qualifier = DEFAULT_KTOR_CLIENT_QUALIFIER) { defaultKtorClient() }
    single<TokenHolder> { TokenHolderImpl(get()) }
    single<TokenRefresher> { TokenRefresherImpl(get(qualifier = DEFAULT_KTOR_CLIENT_QUALIFIER)) }
    single(qualifier = AUTHORIZED_KTOR_CLIENT_QUALIFIER) { authorizedKtorClient(get(), get()) }
}