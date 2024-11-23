package goroh.stepan.features.home.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import coursemeters.features.home.profile.presentation.generated.resources.Res
import coursemeters.features.home.profile.presentation.generated.resources.firstname
import coursemeters.features.home.profile.presentation.generated.resources.lastname
import coursemeters.features.home.profile.presentation.generated.resources.profile
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state = viewModel.state.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }
    when (state) {
        is ProfileState.Success -> {
            ProfileScreen(state = state)
        }

        else -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text("Что я тут делаю?")
            }
        }
    }
}

@Composable
fun ProfileScreen(state: ProfileState.Success) {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Text(
                stringResource(Res.string.profile),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
            ProfileDataItem(title = stringResource(Res.string.firstname), value = state.firstname)
            ProfileDataItem(title = stringResource(Res.string.lastname), value = state.lastname)
        }
    }
}

@Composable
fun ProfileDataItem(title: String, value: String) {
    Card(colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(
            Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("$title:", color = Color.Black)
            Text(value, color = Color.Black)
        }
    }
}