package com.hcato.assistantvirtual.core.network

import com.hcato.assistantvirtual.features.updatestatus.data.datasource.local.model.UpdateStatusRequest
import retrofit2.http.Body
import retrofit2.http.PUT

interface UpdateStatusApi {
    @PUT("user/updateStatus")
    suspend fun updateStatus(
        @Body request: UpdateStatusRequest
    )
}