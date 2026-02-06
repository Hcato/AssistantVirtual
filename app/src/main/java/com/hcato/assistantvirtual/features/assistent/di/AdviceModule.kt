package com.hcato.assistantvirtual.features.assistent.di

import com.hcato.assistantvirtual.core.di.AppContainer
import com.hcato.assistantvirtual.features.assistent.data.datasource.local.translator.MlKitTranslatorRepository
import com.hcato.assistantvirtual.features.assistent.domain.repositories.TranslatorRepository
import com.hcato.assistantvirtual.features.assistent.domain.usecases.GetAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.domain.usecases.TranslateAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.presentation.viewmodels.AdviceViewModelFactory
import com.hcato.assistantvirtual.features.deleteuser.domain.usecase.DeleteUserUseCase
import com.hcato.assistantvirtual.features.updatestatus.domain.repositories.UpdateStatusRepository
import com.hcato.assistantvirtual.features.updatestatus.domain.usecases.UpdateStatusUseCase

class AdviceModule (
    private val appContainer: AppContainer
){
    private fun provideGetAdviceUseCase(): GetAdviceUseCase{
        return GetAdviceUseCase(appContainer.adviceRepository)
    }
    fun provideAdviceViewModelFactory(): AdviceViewModelFactory{
        return AdviceViewModelFactory(
            provideGetAdviceUseCase(), provideTranslateAdviceUseCase(),provideGetUpdateStatusUseCase(),provideGetDeleteUserUseCase()
        )
    }
    fun provideTranslatorRepository(): TranslatorRepository =
        MlKitTranslatorRepository()

    fun provideTranslateAdviceUseCase(): TranslateAdviceUseCase =
        TranslateAdviceUseCase(provideTranslatorRepository())


    private fun provideGetUpdateStatusUseCase(): UpdateStatusUseCase{
        return UpdateStatusUseCase(appContainer.updateStatusRepository)
    }
    private fun provideGetDeleteUserUseCase(): DeleteUserUseCase{
        return DeleteUserUseCase(appContainer.deleteUserRepository)
    }
}