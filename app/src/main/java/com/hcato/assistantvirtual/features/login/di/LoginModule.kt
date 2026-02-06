package com.hcato.assistantvirtual.features.login.di

import com.hcato.assistantvirtual.core.di.AppContainer
import com.hcato.assistantvirtual.features.assistent.data.datasource.local.translator.MlKitTranslatorRepository
import com.hcato.assistantvirtual.features.assistent.domain.repositories.TranslatorRepository
import com.hcato.assistantvirtual.features.assistent.domain.usecases.GetAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.domain.usecases.TranslateAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.presentation.viewmodels.AdviceViewModelFactory
import com.hcato.assistantvirtual.features.login.domain.usecases.LoginUseCase
import com.hcato.assistantvirtual.features.login.presentation.viewmodels.LoginViewModel
import com.hcato.assistantvirtual.features.login.presentation.viewmodels.LoginViewModelFactory

class LoginModule(
    private val appContainer: AppContainer
) {

    private fun provideLoginUseCase(): LoginUseCase {
        return LoginUseCase(
            repository = appContainer.loginRepository,
            tokenDataStore = appContainer.tokenDataStore,
            userSessionDataStore = appContainer.userSessionDataStore
        )
    }

    fun provideLoginViewModelFactory(): LoginViewModelFactory {
        return LoginViewModelFactory(provideLoginUseCase())
    }
}