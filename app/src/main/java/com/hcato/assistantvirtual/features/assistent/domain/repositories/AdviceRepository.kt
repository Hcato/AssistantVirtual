package com.hcato.assistantvirtual.features.assistent.domain.repositories

import com.hcato.assistantvirtual.features.assistent.domain.entities.Advice

interface AdviceRepository {
    suspend fun getAdvice(): Advice
}