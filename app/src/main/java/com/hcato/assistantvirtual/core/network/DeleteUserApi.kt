package com.hcato.assistantvirtual.core.network

import retrofit2.http.DELETE
import retrofit2.http.Path

interface DeleteUserApi {

    @DELETE("user/{id}")
    suspend fun deleteUser(
        @Path("id") userId: Int
    )
}