package goroh.stepan.root

import goroh.stepan.features.auth.presentation.di.authModule
import goroh.stepan.core.di.coreModule
import goroh.stepan.features.home.meters.presentation.di.metersModule
import goroh.stepan.features.home.profile.di.profileModule
import goroh.stepan.features.splash.splashModule
import org.koin.dsl.module

val rootModule = module {
    includes(
        metersModule,
        authModule,
        splashModule,
        coreModule,
        profileModule
    )
}