package goroh.stepan.features.home.meters.presentation.meters

import androidx.navigation.NavController
import goroh.stepan.features.home.meters.presentation.MetersRoute

fun NavController.navigateAccountMeters(id: String) {
    navigate("${MetersRoute.AccountMeters.formatRoute}/$id")
}