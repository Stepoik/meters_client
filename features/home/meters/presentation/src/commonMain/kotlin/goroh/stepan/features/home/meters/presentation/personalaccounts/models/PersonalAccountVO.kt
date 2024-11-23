package goroh.stepan.features.home.meters.presentation.personalaccounts.models

import goroh.stepan.features.home.meters.domain.models.PersonalAccount

data class PersonalAccountVO(
    val id: String,
    val isReadingsTransmitted: Boolean,
    val isPayRequired: Boolean,
    val paymentAmount: String
) {
    constructor(personalAccount: PersonalAccount) : this(
        id = personalAccount.id,
        isReadingsTransmitted = !personalAccount.isReadingRequired,
        isPayRequired = personalAccount.paymentAmount != 0f,
        paymentAmount = "${personalAccount.paymentAmount.toInt()} Р"
    )
}
