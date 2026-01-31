package com.hcato.assistantvirtual.features.assistent.domain.repositories

interface TranslatorRepository {
    suspend fun translateToSpanish(text: String): String
}