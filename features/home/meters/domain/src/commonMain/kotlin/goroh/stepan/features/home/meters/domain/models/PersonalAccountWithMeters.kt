package goroh.stepan.features.home.meters.domain.models

data class PersonalAccountWithMeters(
    val personalAccount: PersonalAccount,
    val meters: List<Meter>
)
