package goroh.stepan.features.auth.presentation.register.validation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import goroh.stepan.features.auth.domain.usecases.RegisterInteractor
import goroh.stepan.uikit.MetersTheme
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RegisterValidationScreen(
    parentNavController: NavController,
    registerInteractor: RegisterInteractor
) {
    val viewModel = koinViewModel<RegisterValidationViewModel> { parametersOf(registerInteractor) }
    val state = viewModel.state.collectAsState().value
    val presenter =
        RegisterValidationPresenterImpl(
            viewModel = viewModel,
            parentNavController = parentNavController
        )
    RegisterValidationScreen(presenter = presenter, state = state)
}

@Composable
fun RegisterValidationScreen(
    presenter: RegisterValidationPresenter,
    state: RegisterValidationScreenState
) {
    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            presenter.navigateHome()
        }
    }

    var verificationCode by rememberSaveable { mutableStateOf("") }

    MetersTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Verification code", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = verificationCode,
                    onValueChange = { verificationCode = it },
                    label = { Text("Verification code") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        presenter.signUp(verificationCode = verificationCode)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}