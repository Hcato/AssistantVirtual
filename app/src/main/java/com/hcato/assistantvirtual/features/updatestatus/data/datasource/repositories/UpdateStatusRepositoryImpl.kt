package com.hcato.assistantvirtual.features.updatestatus.data.datasource.repositories

import com.hcato.assistantvirtual.core.network.UpdateStatusApi
import com.hcato.assistantvirtual.features.updatestatus.data.datasource.local.model.UpdateStatusRequest
import com.hcato.assistantvirtual.features.updatestatus.domain.repositories.UpdateStatusRepository

class UpdateStatusRepositoryImpl(
    private val api: UpdateStatusApi
) : UpdateStatusRepository {

    override suspend fun updateStatus(premium: Boolean) {
        api.updateStatus(UpdateStatusRequest(premium))
    }
}