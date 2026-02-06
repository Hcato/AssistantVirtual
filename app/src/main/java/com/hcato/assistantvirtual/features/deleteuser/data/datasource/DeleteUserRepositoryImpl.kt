package com.hcato.assistantvirtual.features.deleteuser.data.datasource

import com.hcato.assistantvirtual.core.network.DeleteUserApi
import com.hcato.assistantvirtual.features.deleteuser.domain.repository.DeleteUserRepository
import com.hcato.assistantvirtual.features.login.presentation.components.TokenDataStore
import com.hcato.assistantvirtual.features.login.presentation.components.UserSessionDataStore
import kotlinx.coroutines.flow.first

class DeleteUserRepositoryImpl (
    private val api: DeleteUserApi,
    private val userSessionDataStore: UserSessionDataStore,
    private val tokenDataStore: TokenDataStore
): DeleteUserRepository {

    override suspend fun deleteCurrentUser() {
        val userId = userSessionDataStore.userIdFlow.first()
            ?: throw Exception("User ID no disponible")

        api.deleteUser(userId)

        tokenDataStore.clearToken()
        userSessionDataStore.clearUser()
    }
}