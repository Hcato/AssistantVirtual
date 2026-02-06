package com.hcato.assistantvirtual.features.login.presentation.components

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

private val Context.userDataStore by preferencesDataStore("user_session")

class UserSessionDataStore(context: Context) {

    private val dataStore = context.userDataStore

    companion object {
        val USER_ID = intPreferencesKey("user_id")
        val NAME = stringPreferencesKey("name")
        val SURNAMES = stringPreferencesKey("surnames")
        val EMAIL = stringPreferencesKey("email")
        val PREMIUM = booleanPreferencesKey("premium")
    }

    suspend fun saveUser(
        userId: Int,
        name: String,
        surnames: String,
        email: String,
        premium: Boolean
    ) {
        dataStore.edit { prefs ->
            prefs[USER_ID] = userId
            prefs[NAME] = name
            prefs[SURNAMES] = surnames
            prefs[EMAIL] = email
            prefs[PREMIUM] = premium
        }
    }

    val userIdFlow: Flow<Int?> =
        dataStore.data.map { it[USER_ID] }

    val isPremiumFlow: Flow<Boolean> =
        dataStore.data.map { it[PREMIUM] ?: false }
    class AuthInterceptor(
        private val tokenDataStore: TokenDataStore
    ) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()

            val token = runBlocking {
                tokenDataStore.tokenFlow.first()
            }

            val request = if (!token.isNullOrEmpty()) {
                originalRequest.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                originalRequest
            }

            return chain.proceed(request)
        }
    }
    suspend fun clearUser() {
        dataStore.edit { it.clear() }
    }
}