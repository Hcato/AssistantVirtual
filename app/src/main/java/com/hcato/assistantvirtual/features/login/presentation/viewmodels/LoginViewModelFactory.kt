package com.hcato.assistantvirtual.features.login.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hcato.assistantvirtual.features.login.domain.usecases.LoginUseCase

class LoginViewModelFactory (
    private val getLoginUseCase: LoginUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(getLoginUseCase) as T
        }
        throw IllegalArgumentException("Unknow viewmodel class: ${modelClass.name}")
    }
}