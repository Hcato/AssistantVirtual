package com.hcato.assistantvirtual.features.assistent.data.datasource.repositories

import com.hcato.assistantvirtual.core.network.AdviceApi
import com.hcato.assistantvirtual.features.assistent.data.datasource.remote.mapper.toDomain
import com.hcato.assistantvirtual.features.assistent.domain.entities.Advice
import com.hcato.assistantvirtual.features.assistent.domain.repositories.AdviceRepository

class AdviceRepositoryImpl (
    private val api: AdviceApi
): AdviceRepository{
    override suspend fun getAdvice(): Advice {
        return api.getAdvice().slip.toDomain()
    }
}