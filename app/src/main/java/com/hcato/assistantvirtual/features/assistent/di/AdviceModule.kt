package com.hcato.assistantvirtual.features.assistent.di

import com.hcato.assistantvirtual.core.di.AppContainer
import com.hcato.assistantvirtual.features.assistent.data.datasource.local.translator.MlKitTranslatorRepository
import com.hcato.assistantvirtual.features.assistent.domain.repositories.TranslatorRepository
import com.hcato.assistantvirtual.features.assistent.domain.usecases.GetAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.domain.usecases.TranslateAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.presentation.viewmodels.AdviceViewModelFactory

class AdviceModule (
    private val appContainer: AppContainer
){
    private fun provideGetAdviceUseCase(): GetAdviceUseCase{
        return GetAdviceUseCase(appContainer.adviceRepository)
    }
    fun provideAdviceViewModelFactory(): AdviceViewModelFactory{
        return AdviceViewModelFactory(
            provideGetAdviceUseCase(), provideTranslateAdviceUseCase()
        )
    }
    fun provideTranslatorRepository(): TranslatorRepository =
        MlKitTranslatorRepository()

    fun provideTranslateAdviceUseCase(): TranslateAdviceUseCase =
        TranslateAdviceUseCase(provideTranslatorRepository())
}