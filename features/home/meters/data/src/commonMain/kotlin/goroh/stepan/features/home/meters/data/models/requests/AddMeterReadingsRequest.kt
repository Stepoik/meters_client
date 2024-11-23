package goroh.stepan.features.home.meters.data.models.requests

import goroh.stepan.features.home.meters.domain.models.MeterReading
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddMeterReadingsRequest(
    @SerialName("readings")
    val metersReadings: List<MeterReadingInsertDto>
)

fun AddMeterReadingsRequest(readings: List<MeterReading>) = AddMeterReadingsRequest(
    metersReadings = readings.map(::MeterReadingInsertDto)
)

@Serializable
data class MeterReadingInsertDto(
    @SerialName("id")
    val meterId: String,
    @SerialName("reading")
    val reading: Float
) {
    constructor(reading: MeterReading) : this(
        meterId = reading.id,
        reading = reading.value
    )
}