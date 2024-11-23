package goroh.stepan.features.home.meters.data.models.responses

import goroh.stepan.features.home.meters.domain.models.Meter
import goroh.stepan.features.home.meters.domain.models.MeterType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMetersResponse(
    @SerialName("meters")
    val meters: List<MeterWithReadingDto>
)

@Serializable
data class MeterWithReadingDto(
    val meter: MeterDto,
    @SerialName("last_reading")
    val lastReading: MeterReadingDto?
)

@Serializable
data class MeterDto(
    val id: String,
    val type: MeterType,
    @SerialName("personal_account")
    val personalAccount: String
) {
    fun toDomain(): Meter {
        return Meter(
            id = id,
            type = type
        )
    }
}


@Serializable
data class MeterReadingDto(
    val id: String,
    val reading: Float,
    val date: Long
)
