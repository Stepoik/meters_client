package goroh.stepan.features.home.profile.data

import goroh.stepan.core.net.BASE_URL
import goroh.stepan.features.home.profile.data.models.GetProfileResponse
import goroh.stepan.features.home.profile.domain.ProfileRepository
import goroh.stepan.features.home.profile.domain.models.ProfileData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ProfileRepositoryImpl(
    private val httpClient: HttpClient
) : ProfileRepository {
    override suspend fun getProfile(): Result<ProfileData> {
        return runCatching {
            val body = httpClient.get("$BASE_URL/profile/"){
                contentType(ContentType.Application.Json)
            }.body<GetProfileResponse>()
            ProfileData(firstname = body.firstname, lastname = body.lastname)
        }
    }
}