package goroh.stepan.features.home.meters.data

import goroh.stepan.core.net.BASE_URL
import goroh.stepan.features.home.meters.data.models.responses.GetPersonalAccountWithMetersResponse
import goroh.stepan.features.home.meters.data.models.responses.GetPersonalAccountsResponse
import goroh.stepan.features.home.meters.data.models.PersonalAccountDto
import goroh.stepan.features.home.meters.data.models.requests.AddMeterReadingsRequest
import goroh.stepan.features.home.meters.data.models.requests.AddPersonalAccountRequest
import goroh.stepan.features.home.meters.data.models.requests.PaymentRequest
import goroh.stepan.features.home.meters.domain.PersonalAccountsRepository
import goroh.stepan.features.home.meters.domain.models.PersonalAccount
import goroh.stepan.features.home.meters.domain.models.PersonalAccountReading
import goroh.stepan.features.home.meters.domain.models.PersonalAccountWithMeters
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class PersonalAccountRepositoryImpl(
    private val httpClient: HttpClient
) : PersonalAccountsRepository {
    override suspend fun getPersonalAccounts(): Result<List<PersonalAccount>> {
        return runCatching {
            httpClient.get("$BASE_URL/meters/personal_account") {
                contentType(ContentType.Application.Json)
            }.body<GetPersonalAccountsResponse>()
                .personalAccounts
                .map(PersonalAccountDto::toDomain)
        }
    }

    override suspend fun getPersonalAccountWithMeters(id: String): Result<PersonalAccountWithMeters> {
        return runCatching {
            val body = httpClient.get("$BASE_URL/meters/personal_account/$id") {
                contentType(ContentType.Application.Json)
            }.body<GetPersonalAccountWithMetersResponse>()
            body.toDomain()
        }
    }

    override suspend fun addPersonalAccount(id: String): Result<Any?> {
        return runCatching {
            val response = httpClient.post("$BASE_URL/meters/personal_account") {
                contentType(ContentType.Application.Json)
                setBody(AddPersonalAccountRequest(id))
            }
            check(response.status == HttpStatusCode.OK)
        }
    }

    override suspend fun sendMetersReadings(personalAccountReading: PersonalAccountReading): Result<Any?> {
        return runCatching {
            val response = httpClient.post("$BASE_URL/meters/meters/readings") {
                contentType(ContentType.Application.Json)
                setBody(AddMeterReadingsRequest(personalAccountReading.metersReadings))
            }
            check(response.status == HttpStatusCode.OK)
        }
    }

    override suspend fun payForMeters(id: String): Result<Any?> {
        return runCatching {
            val response = httpClient.post("$BASE_URL/meters/payment") {
                contentType(ContentType.Application.Json)
                setBody(PaymentRequest(id))
            }
            check(response.status == HttpStatusCode.OK)
        }
    }
}