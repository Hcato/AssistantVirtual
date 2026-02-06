package com.hcato.assistantvirtual.features.register.data.datasource.repositories

import com.hcato.assistantvirtual.core.network.RegisterApi
import com.hcato.assistantvirtual.features.register.data.datasource.local.model.RegisterRequest
import com.hcato.assistantvirtual.features.register.domain.repositories.RegisterRepository

class RegisterRepositoryImpl(
    private val api: RegisterApi
) : RegisterRepository {

    override suspend fun register(
        name: String,
        surnames: String,
        email: String,
        password: String,
        premium: Boolean,
        deviceId: Int
    ) {
        api.register(
            RegisterRequest(
                name = name,
                surnames = surnames,
                email = email,
                password = password,
                premium = premium,
                deviceId = deviceId
            )
        )
    }
}
