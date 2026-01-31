package com.hcato.assistantvirtual.features.assistent.domain.usecases

import com.hcato.assistantvirtual.features.assistent.domain.entities.Advice
import com.hcato.assistantvirtual.features.assistent.domain.repositories.AdviceRepository

class GetAdviceUseCase (
    private val repository: AdviceRepository
){
    suspend operator fun invoke(): Advice{
        return repository.getAdvice()
    }
}