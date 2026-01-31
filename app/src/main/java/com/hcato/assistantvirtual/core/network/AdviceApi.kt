package com.hcato.assistantvirtual.core.network

import com.hcato.assistantvirtual.features.assistent.data.datasource.remote.model.AdviceResponse
import retrofit2.http.GET

interface AdviceApi {
    @GET("advice")
    suspend fun getAdvice(): AdviceResponse
}