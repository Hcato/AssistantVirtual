package com.hcato.assistantvirtual.features.login.domain.usecases

import android.util.Log
import com.hcato.assistantvirtual.features.login.domain.entities.LoginResult
import com.hcato.assistantvirtual.features.login.domain.repositories.LoginRepository
import com.hcato.assistantvirtual.features.login.presentation.components.TokenDataStore
import com.hcato.assistantvirtual.features.login.presentation.components.UserSessionDataStore

class LoginUseCase (
    private val repository: LoginRepository,
    private val tokenDataStore: TokenDataStore,
    private val userSessionDataStore: UserSessionDataStore
) {
    suspend operator fun invoke(email: String, password: String) {
        val result = repository.login(email, password)

        result.token
            .takeIf { it.isNotBlank() }
            ?.let { tokenDataStore.saveToken(it) }

        userSessionDataStore.saveUser(
            userId = result.user.id,
            name = result.user.name,
            surnames = result.user.surnames,
            email = result.user.email,
            premium = result.user.isPremium
        )
        Log.d("LOGIN", "User guardado con id=${result.user.id}")
    }
}