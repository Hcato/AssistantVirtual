package com.hcato.assistantvirtual.core.network

import com.hcato.assistantvirtual.features.register.data.datasource.local.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("user")
    suspend fun register(
        @Body request: RegisterRequest
    )
}