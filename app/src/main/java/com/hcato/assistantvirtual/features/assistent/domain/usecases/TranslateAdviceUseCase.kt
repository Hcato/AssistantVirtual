package com.hcato.assistantvirtual.features.assistent.domain.usecases

import com.hcato.assistantvirtual.features.assistent.domain.repositories.TranslatorRepository

class TranslateAdviceUseCase(
    private val repository: TranslatorRepository
) {
    suspend operator fun invoke(text: String): String {
        return repository.translateToSpanish(text)
    }
}
