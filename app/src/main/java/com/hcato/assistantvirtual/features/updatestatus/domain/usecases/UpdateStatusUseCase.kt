package com.hcato.assistantvirtual.features.updatestatus.domain.usecases

import com.hcato.assistantvirtual.features.updatestatus.domain.repositories.UpdateStatusRepository

class UpdateStatusUseCase (
    private val repository: UpdateStatusRepository
) {
    suspend operator fun invoke(premium: Boolean) {
        repository.updateStatus(premium)
    }
}