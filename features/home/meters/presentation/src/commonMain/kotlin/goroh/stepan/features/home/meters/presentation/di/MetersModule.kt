package goroh.stepan.features.home.meters.presentation.di

import goroh.stepan.core.di.AUTHORIZED_KTOR_CLIENT_QUALIFIER
import goroh.stepan.features.home.meters.data.PersonalAccountRepositoryImpl
import goroh.stepan.features.home.meters.domain.PersonalAccountsRepository
import goroh.stepan.features.home.meters.presentation.meters.AccountMetersViewModel
import goroh.stepan.features.home.meters.presentation.newpersonalaccount.NewPersonalAccountViewModel
import goroh.stepan.features.home.meters.presentation.personalaccounts.PersonalAccountsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val metersModule = module {
    single<PersonalAccountsRepository> { PersonalAccountRepositoryImpl(get(qualifier = AUTHORIZED_KTOR_CLIENT_QUALIFIER)) }

    viewModel { parameters -> AccountMetersViewModel(parameters.get(), get()) }
    viewModel { PersonalAccountsViewModel(get()) }
    viewModel { NewPersonalAccountViewModel(get()) }
}