package goroh.stepan.features.home.meters.domain

import goroh.stepan.features.home.meters.domain.models.PersonalAccount
import goroh.stepan.features.home.meters.domain.models.PersonalAccountReading
import goroh.stepan.features.home.meters.domain.models.PersonalAccountWithMeters

interface PersonalAccountsRepository {
    suspend fun getPersonalAccounts(): Result<List<PersonalAccount>>

    suspend fun getPersonalAccountWithMeters(id: String): Result<PersonalAccountWithMeters>

    suspend fun addPersonalAccount(id: String): Result<Any?>

    suspend fun sendMetersReadings(personalAccountReading: PersonalAccountReading): Result<Any?>

    suspend fun payForMeters(id: String): Result<Any?>
}