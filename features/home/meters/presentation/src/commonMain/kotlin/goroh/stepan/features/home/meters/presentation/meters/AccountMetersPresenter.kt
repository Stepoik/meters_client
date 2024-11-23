package goroh.stepan.features.home.meters.presentation.meters

interface AccountMetersPresenter {
    fun loadMeters()

    fun setMeterReading(id: String, value: String)

    fun sendMeters()

    fun navigateBack()
}