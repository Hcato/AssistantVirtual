package com.hcato.assistantvirtual.features.register.domain.repositories

interface RegisterRepository {
    suspend fun register(
        name: String,
        surnames: String,
        email: String,
        password: String,
        premium: Boolean,
        deviceId: Int
    )
}