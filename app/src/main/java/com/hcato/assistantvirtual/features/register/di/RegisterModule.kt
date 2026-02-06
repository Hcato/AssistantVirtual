package com.hcato.assistantvirtual.features.register.di

import com.hcato.assistantvirtual.core.di.AppContainer
import com.hcato.assistantvirtual.features.register.domain.usecases.RegisterUseCase
import com.hcato.assistantvirtual.features.register.presentation.viewmodels.RegisterViewModelFactory

class RegisterModule(
    private val appContainer: AppContainer
) {

    private fun provideRegisterUseCase(): RegisterUseCase {
        return RegisterUseCase(appContainer.registerRepository)
    }

    fun provideRegisterViewModelFactory(): RegisterViewModelFactory {
        return RegisterViewModelFactory(
            provideRegisterUseCase()
        )
    }
}