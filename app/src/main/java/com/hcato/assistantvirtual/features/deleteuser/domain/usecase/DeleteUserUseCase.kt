package com.hcato.assistantvirtual.features.deleteuser.domain.usecase

import com.hcato.assistantvirtual.features.deleteuser.domain.repository.DeleteUserRepository

class DeleteUserUseCase (
    private val repository: DeleteUserRepository
) {
    suspend operator fun invoke() {
        repository.deleteCurrentUser()
    }
}