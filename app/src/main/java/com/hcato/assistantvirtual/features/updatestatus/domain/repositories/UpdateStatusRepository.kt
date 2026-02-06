package com.hcato.assistantvirtual.features.updatestatus.domain.repositories

interface UpdateStatusRepository {
    suspend fun updateStatus(premium: Boolean)
}