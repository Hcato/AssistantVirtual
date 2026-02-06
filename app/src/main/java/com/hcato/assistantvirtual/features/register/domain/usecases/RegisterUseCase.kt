package com.hcato.assistantvirtual.features.register.domain.usecases

import com.hcato.assistantvirtual.features.register.domain.repositories.RegisterRepository

class RegisterUseCase(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(
        name: String,
        surnames: String,
        email: String,
        password: String,
        premium: Boolean,
        deviceId: Int
    ) {
        repository.register(
            name,
            surnames,
            email,
            password,
            premium,
            deviceId
        )
    }
}
