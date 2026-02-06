package com.hcato.assistantvirtual.features.login.data.datasource.repositories

import com.hcato.assistantvirtual.core.network.LoginApi
import com.hcato.assistantvirtual.features.login.data.datasource.local.mapper.toDomain
import com.hcato.assistantvirtual.features.login.data.datasource.local.model.LoginRequestDto
import com.hcato.assistantvirtual.features.login.domain.entities.LoginResult
import com.hcato.assistantvirtual.features.login.domain.repositories.LoginRepository
import com.hcato.assistantvirtual.features.login.presentation.components.TokenDataStore

class LoginRepositoryImpl(
    private val api: LoginApi
) : LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): LoginResult {

        val response = api.login(
            LoginRequestDto(email, password)
        )

        return LoginResult(
            token = response.token,
            user = response.user.toDomain()
        )
    }
}