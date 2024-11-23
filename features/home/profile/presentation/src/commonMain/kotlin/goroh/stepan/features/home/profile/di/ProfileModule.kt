package goroh.stepan.features.home.profile.di

import goroh.stepan.core.di.AUTHORIZED_KTOR_CLIENT_QUALIFIER
import goroh.stepan.features.home.profile.ProfileViewModel
import goroh.stepan.features.home.profile.data.ProfileRepositoryImpl
import goroh.stepan.features.home.profile.domain.ProfileRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    single<ProfileRepository> { ProfileRepositoryImpl(get(qualifier = AUTHORIZED_KTOR_CLIENT_QUALIFIER)) }

    viewModel { ProfileViewModel(get()) }
}