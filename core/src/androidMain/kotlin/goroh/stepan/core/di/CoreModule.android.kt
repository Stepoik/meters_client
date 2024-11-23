package goroh.stepan.core.di

import goroh.stepan.core.preferences.dataStore
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual fun platformCoreModule() = module {
    single { dataStore(get()) }
}