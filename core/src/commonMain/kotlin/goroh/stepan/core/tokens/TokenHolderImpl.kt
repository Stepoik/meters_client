package goroh.stepan.core.tokens

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

private object TokenScheme {
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
}

internal class TokenHolderImpl(
    private val preferences: DataStore<Preferences>
): TokenHolder {
    override suspend fun saveTokens(tokens: Tokens?) {
        if (tokens == null) {
            preferences.edit { preferences ->
                preferences.remove(TokenScheme.REFRESH_TOKEN)
                preferences.remove(TokenScheme.ACCESS_TOKEN)
            }
        } else {
            preferences.edit { preferences ->
                preferences[TokenScheme.ACCESS_TOKEN] = tokens.accessToken
                preferences[TokenScheme.REFRESH_TOKEN] = tokens.refreshToken
            }
        }
    }

    override suspend fun getTokens(): Tokens? {
        val data = preferences.data.first()
        val accessToken = data[TokenScheme.ACCESS_TOKEN] ?: return null
        val refreshToken = data[TokenScheme.REFRESH_TOKEN] ?: return null
        return Tokens(accessToken = accessToken, refreshToken = refreshToken)
    }
}