package goroh.stepan.features.auth.presentation.register

import androidx.lifecycle.ViewModel
import goroh.stepan.features.auth.domain.usecases.RegisterInteractor

class RegisterSharedViewModel(
    val registerInteractor: RegisterInteractor
) : ViewModel() {
}