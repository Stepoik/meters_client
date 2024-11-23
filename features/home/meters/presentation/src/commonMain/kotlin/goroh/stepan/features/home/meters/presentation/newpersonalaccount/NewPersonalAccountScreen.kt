package goroh.stepan.features.home.meters.presentation.newpersonalaccount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coursemeters.features.home.meters.presentation.generated.resources.Res
import coursemeters.features.home.meters.presentation.generated.resources.add_account
import coursemeters.features.home.meters.presentation.generated.resources.new_personal_account
import goroh.stepan.uikit.buttons.BaseButton
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewPersonalAccountScreen(navController: NavController) {
    val viewModel = koinViewModel<NewPersonalAccountViewModel>()
    val state = viewModel.state.collectAsState().value
    val presenter =
        NewPersonalAccountPresenterImpl(viewModel = viewModel, navController = navController)
    NewPersonalAccountScreen(presenter = presenter, state = state)
}

@Composable
fun NewPersonalAccountScreen(
    presenter: NewPersonalAccountPresenter,
    state: NewPersonalAccountState
) {
    var personalAccount by remember {
        mutableStateOf("")
    }
    LaunchedEffect(state.isAdded) {
        if (state.isAdded) {
            presenter.navigateBack()
        }
    }
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(Res.string.new_personal_account),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = personalAccount,
                onValueChange = { personalAccount = it },
                label = { Text(stringResource(Res.string.new_personal_account)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            BaseButton(
                text = stringResource(Res.string.add_account),
                onClick = { presenter.addPersonalAccount(personalAccount) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}