package goroh.stepan.features.auth.presentation.register.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import goroh.stepan.features.auth.domain.models.RegisterCredentials
import goroh.stepan.features.auth.domain.usecases.RegisterInteractor
import goroh.stepan.uikit.MetersTheme
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RegisterMainScreen(navController: NavController, registerInteractor: RegisterInteractor) {
    val viewModel = koinViewModel<RegisterMainViewModel> { parametersOf(registerInteractor) }
    val state = viewModel.state.collectAsState().value
    val presenter = RegisterMainPresenterImpl(viewModel = viewModel, navController = navController)
    RegisterMainScreen(presenter = presenter, state = state)
}

@Composable
fun RegisterMainScreen(
    presenter: RegisterMainPresenter,
    state: RegisterMainScreenState
) {
    LaunchedEffect(state.isSent) {
        if (state.isSent) {
            presenter.navigateValidation()
        }
    }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }

    MetersTheme {
        Scaffold {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    Column {
                        Text("Register", style = MaterialTheme.typography.headlineMedium)

                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = { Text("Confirm Password") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = firstname,
                            onValueChange = { firstname = it },
                            label = { Text("First name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = lastname,
                            onValueChange = { lastname = it },
                            label = { Text("Last name") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                presenter.setCredentials(
                                    registerCredentials = RegisterCredentials(
                                        email = email,
                                        password = password,
                                        confirmPassword = confirmPassword,
                                        firstname = firstname,
                                        lastname = lastname
                                    )
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Register")
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal expect fun RegisterScreenPreview()