package goroh.stepan.features.home.meters.presentation.meters.models

import goroh.stepan.features.home.meters.domain.models.PersonalAccountWithMeters

data class PersonalAccountVO(
    val title: String,
    val isReadingRequired: Boolean,
    val meters: List<MetersVO>
) {
    constructor(personalAccount: PersonalAccountWithMeters) : this(
        title = personalAccount.personalAccount.id,
        isReadingRequired = personalAccount.personalAccount.isReadingRequired,
        meters = personalAccount.meters.map(::MetersVO)
    )
}
