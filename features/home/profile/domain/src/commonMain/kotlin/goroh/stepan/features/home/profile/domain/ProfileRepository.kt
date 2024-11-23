package goroh.stepan.features.home.profile.domain

import goroh.stepan.features.home.profile.domain.models.ProfileData

interface ProfileRepository {
    suspend fun getProfile(): Result<ProfileData>
}