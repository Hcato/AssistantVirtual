package com.hcato.assistantvirtual.features.deleteuser.domain.repository


interface DeleteUserRepository {
    suspend fun deleteCurrentUser()
}