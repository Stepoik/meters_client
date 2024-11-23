package goroh.stepan.features.home.meters.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import goroh.stepan.features.home.meters.presentation.meters.AccountMetersScreen
import goroh.stepan.features.home.meters.presentation.newpersonalaccount.NewPersonalAccountScreen
import goroh.stepan.features.home.meters.presentation.personalaccounts.PersonalAccountsList
import goroh.stepan.feautres.home.flow.HomeRoute

private val BASE_ROUTE = HomeRoute.Meters.route

internal sealed class MetersRoute(val route: String, val formatRoute: String = route) {
    data object PersonalAccounts : MetersRoute("$BASE_ROUTE/personal_accounts")
    data object AccountMeters :
        MetersRoute(route = "$BASE_ROUTE/meters/{id}", formatRoute = "$BASE_ROUTE/meters")
    data object NewPersonalAccount : MetersRoute("$BASE_ROUTE/new_personal_account")
}

fun NavGraphBuilder.meters(navController: NavController) {
    navigation(route = BASE_ROUTE, startDestination = MetersRoute.PersonalAccounts.route) {
        composable(MetersRoute.PersonalAccounts.route) {
            PersonalAccountsList(navController)
        }

        composable(
            MetersRoute.AccountMeters.route,
            arguments = listOf(navArgument("id", { NavType.StringType }))
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")!!
            AccountMetersScreen(personalAccountId = id, navController = navController)
        }

        composable(MetersRoute.NewPersonalAccount.route) {
            NewPersonalAccountScreen(navController)
        }
    }
}