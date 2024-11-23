package goroh.stepan.features.home.meters.presentation.meters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coursemeters.features.home.meters.presentation.generated.resources.Res
import coursemeters.features.home.meters.presentation.generated.resources.send_readings
import goroh.stepan.features.home.meters.presentation.meters.models.MetersVO
import goroh.stepan.uikit.buttons.BaseButton
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountMetersScreen(personalAccountId: String, navController: NavController) {
    val viewModel = koinViewModel<AccountMetersViewModel> { parametersOf(personalAccountId) }
    val presenter = AccountMetersPresenterImpl(navController = navController, viewModel = viewModel)
    val state = viewModel.state.collectAsState().value
    LaunchedEffect(Unit) {
        presenter.loadMeters()
    }
    Box(
        Modifier.pullToRefresh(
            isRefreshing = state is AccountMetersState.Loading,
            state = rememberPullToRefreshState(),
            onRefresh = presenter::loadMeters
        ).fillMaxSize()
    ) {
        when (state) {
            is AccountMetersState.Success -> AccountMetersScreen(
                presenter = presenter,
                state = state
            )

            is AccountMetersState.Loading -> {
                Text("Загружаю")
            }

            else -> {
                Text("Где я?")
            }
        }
    }
}

@Composable
fun AccountMetersScreen(presenter: AccountMetersPresenter, state: AccountMetersState.Success) {
    LaunchedEffect(state.isSent) {
        if (state.isSent) {
            presenter.navigateBack()
        }
    }
    Scaffold {
        LazyColumn(
            Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            header(state)
            meters(
                isReadingRequired = state.personalAccount.isReadingRequired,
                state = state,
                presenter = presenter
            )
            if (state.personalAccount.isReadingRequired && state.personalAccount.meters.isNotEmpty()) {
                sendMetersButton(presenter::sendMeters)
            }
        }
    }
}

internal fun LazyListScope.header(state: AccountMetersState.Success) {
    item {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(state.personalAccount.title, fontSize = 27.sp)
        }
    }
}

internal fun LazyListScope.meters(
    isReadingRequired: Boolean,
    presenter: AccountMetersPresenter,
    state: AccountMetersState.Success
) {
    println(state.metersReadings.values)
    val meters = state.personalAccount.meters
    if (meters.isEmpty()) {
        item {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("Тут пока нет счетчиков", fontSize = 27.sp)
            }
        }
    } else {
        items(meters, key = { it.title }) { meter ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = meter.type,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = meter.title,
                        fontSize = 18.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    if (isReadingRequired) {
                        TextField(
                            value = state.metersReadings[meter.title] ?: "",
                            onValueChange = {
                                presenter.setMeterReading(
                                    id = meter.title,
                                    value = it
                                )
                            },
                            label = { Text(meter.type) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            placeholder = { Text("0") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

internal fun LazyListScope.sendMetersButton(onSend: () -> Unit) {
    item {
        BaseButton(
            text = stringResource(Res.string.send_readings),
            onClick = onSend,
            modifier = Modifier.fillMaxWidth()
        )
    }
}