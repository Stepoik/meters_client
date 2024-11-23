package goroh.stepan.features.auth.presentation.di

import goroh.stepan.core.di.DEFAULT_KTOR_CLIENT_QUALIFIER
import goroh.stepan.features.auth.data.AuthKtorDatastore
import goroh.stepan.features.auth.data.AuthRepositoryImpl
import goroh.stepan.features.auth.domain.AuthRepository
import goroh.stepan.features.auth.domain.usecases.RegisterInteractor
import goroh.stepan.features.auth.presentation.login.LoginViewModel
import goroh.stepan.features.auth.presentation.register.RegisterSharedViewModel
import goroh.stepan.features.auth.presentation.register.main.RegisterMainViewModel
import goroh.stepan.features.auth.presentation.register.validation.RegisterValidationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { AuthKtorDatastore(get(qualifier = DEFAULT_KTOR_CLIENT_QUALIFIER)) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory { RegisterInteractor(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { RegisterSharedViewModel(get()) }
    viewModel { parameters -> RegisterMainViewModel(parameters.get()) }
    viewModel { parameters -> RegisterValidationViewModel(parameters.get()) }
}