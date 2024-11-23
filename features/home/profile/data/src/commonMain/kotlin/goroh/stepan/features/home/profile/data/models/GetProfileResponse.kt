package goroh.stepan.features.home.profile.data.models

import kotlinx.serialization.Serializable

@Serializable
data class GetProfileResponse(
    val id: String,
    val firstname: String,
    val lastname: String
)
