package com.hcato.assistantvirtual.core.network

import com.hcato.assistantvirtual.features.login.data.datasource.local.model.LoginRequestDto
import com.hcato.assistantvirtual.features.login.data.datasource.local.model.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("user/login")
    suspend fun login(
        @Body body: LoginRequestDto
    ): LoginResponseDto
}