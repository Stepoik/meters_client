package goroh.stepan.root

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object KoinSDK {
    fun initKoin(platformModule: Module) {
        startKoin {
            modules(platformModule, rootModule)
        }
    }
}