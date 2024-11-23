package goroh.stepan.feautres.home.flow

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import goroh.stepan.features.common.ApplicationRoute

private val BASE_ROUTE = ApplicationRoute.Home.route

sealed class HomeRoute(val route: String, val icon: ImageVector) {
    data object Meters : HomeRoute("$BASE_ROUTE/meters", icon = Icons.Default.Home)
    data object Profile : HomeRoute("$BASE_ROUTE/profile", icon = Icons.Default.AccountCircle)
}