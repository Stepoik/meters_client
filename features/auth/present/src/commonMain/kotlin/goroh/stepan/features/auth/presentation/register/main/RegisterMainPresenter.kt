package goroh.stepan.features.auth.presentation.register.main

import goroh.stepan.features.auth.domain.models.RegisterCredentials

interface RegisterMainPresenter {
    fun setCredentials(registerCredentials: RegisterCredentials)

    fun navigateValidation()
}