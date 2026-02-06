package com.hcato.assistantvirtual.features.login.domain.repositories

import com.hcato.assistantvirtual.features.login.domain.entities.LoginResult

interface LoginRepository {
    suspend fun login(email: String, password: String): LoginResult
}