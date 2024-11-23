package goroh.stepan.features.home.meters.presentation.personalaccounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coursemeters.features.home.meters.presentation.generated.resources.Res
import coursemeters.features.home.meters.presentation.generated.resources.add_account
import coursemeters.features.home.meters.presentation.generated.resources.pay
import coursemeters.features.home.meters.presentation.generated.resources.personal_accounts
import coursemeters.features.home.meters.presentation.generated.resources.readings_not_transmitted
import coursemeters.features.home.meters.presentation.generated.resources.readings_transmitted
import goroh.stepan.features.home.meters.presentation.personalaccounts.models.PersonalAccountVO
import goroh.stepan.uikit.buttons.BaseButton
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalAccountsList(navController: NavController) {
    val viewModel = koinViewModel<PersonalAccountsViewModel>()
    val state = viewModel.state.collectAsState().value
    val presenter =
        PersonalAccountsPresenterImpl(viewModel = viewModel, navController = navController)
    LaunchedEffect(Unit) {
        presenter.loadAccounts()
    }
    Scaffold {
        Box(
            Modifier.fillMaxSize().pullToRefresh(
                isRefreshing = state is PersonalAccountsState.Loading,
                state = rememberPullToRefreshState(),
                onRefresh = {
                    presenter.loadAccounts()
                })
        ) {
            when (state) {
                is PersonalAccountsState.Loading -> {
                    Text("Гружусь")
                }

                is PersonalAccountsState.Success -> {
                    PersonalAccountsList(presenter = presenter, state = state)
                }

                is PersonalAccountsState.Error -> {
                    Text(state.message)
                }

                else -> {
                    Text("где я?")
                }
            }
        }
    }
}

@Composable
fun PersonalAccountsList(
    presenter: PersonalAccountsPresenter,
    state: PersonalAccountsState.Success
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        header()
        accounts(
            state.accounts,
            onClickAccount = presenter::navigateMeters,
            onClickPay = presenter::pay)
        addAccount(onAddAccount = presenter::navigateNewPersonalAccount)
    }
}

internal fun LazyListScope.header() {
    item {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                stringResource(Res.string.personal_accounts),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

internal fun LazyListScope.accounts(
    accounts: List<PersonalAccountVO>,
    onClickAccount: (id: String) -> Unit,
    onClickPay: (id: String) -> Unit
) {
    items(accounts) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onClickAccount(it.id) },
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Row(
                Modifier.fillMaxWidth().padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(it.id, fontSize = 18.sp)
                    val paymentAmountColor =
                        if (it.isPayRequired) Color(0xFFe74c3c) else Color(0xFF27ae60)
                    Text(it.paymentAmount, color = paymentAmountColor, fontSize = 27.sp)
                    val (color, transmitText) = if (it.isReadingsTransmitted) {
                        Pair(Color(0xFF27ae60), Res.string.readings_transmitted)
                    } else {
                        Pair(Color(0xFFe74c3c), Res.string.readings_not_transmitted)
                    }
                    Text(stringResource(transmitText), color = color, fontSize = 14.sp)
                }
                BaseButton(text = stringResource(Res.string.pay), onClick = { onClickPay(it.id) })
            }
        }
    }
}

internal fun LazyListScope.addAccount(onAddAccount: () -> Unit) {
    item {
        BaseButton(
            text = stringResource(Res.string.add_account),
            onClick = onAddAccount,
            modifier = Modifier.fillParentMaxWidth()
        )
    }
}