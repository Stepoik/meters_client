package goroh.stepan.features.home.profile

sealed class ProfileState {
    data object Idle : ProfileState()
    data object Loading : ProfileState()
    data class Success(
        val firstname: String,
        val lastname: String
    ): ProfileState()
}
