package goroh.stepan.features.home.meters.domain.models

data class PersonalAccount(
    val id: String,
    val isReadingRequired: Boolean,
    val paymentAmount: Float
)
