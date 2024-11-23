package goroh.stepan.features.home.meters.domain.models

data class Meter(
    val id: String,
    val type: MeterType
)

enum class MeterType {
    WATER_COLD,     // Счетчик холодной воды
    WATER_HOT,      // Счетчик горячей воды
    ELECTRICITY_DAY, // Электросчетчик (дневной тариф)
    ELECTRICITY_NIGHT, // Электросчетчик (ночной тариф)
    GAS,            // Газовый счетчик
    HEATING,        // Счетчик отопления
    SEWAGE,         // Счетчик сточных вод (если применяется)
    GENERAL_HOUSE_WATER, // Общедомовой счетчик воды
    GENERAL_HOUSE_HEATING // Общедомовой счетчик отопления
}

