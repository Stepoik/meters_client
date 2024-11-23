package goroh.stepan.features.home.meters.presentation.meters.models

import goroh.stepan.features.home.meters.domain.models.Meter
import goroh.stepan.features.home.meters.domain.models.MeterType

data class MetersVO(
    val title: String,
    val type: String
) {
    constructor(meter: Meter) : this(
        title = meter.id,
        type = meter.type.format()
    )
}

fun MeterType.format(): String {
    return when (this) {
        MeterType.GAS -> "ГАЗ"
        MeterType.SEWAGE -> "ГАЗ"
        MeterType.HEATING -> "Отопление"
        MeterType.WATER_HOT ->"Горячая вода"
        MeterType.WATER_COLD-> "Холодная вода"
        MeterType.ELECTRICITY_DAY -> "Дневное электричество"
        MeterType.ELECTRICITY_NIGHT -> "Ночное электричество"
        MeterType.GENERAL_HOUSE_HEATING-> "Отопление дома"
        MeterType.GENERAL_HOUSE_WATER -> "Вода в доме"
    }
}
