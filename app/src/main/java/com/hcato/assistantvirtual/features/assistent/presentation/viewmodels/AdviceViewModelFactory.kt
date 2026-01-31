package com.hcato.assistantvirtual.features.assistent.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hcato.assistantvirtual.features.assistent.domain.usecases.GetAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.domain.usecases.TranslateAdviceUseCase

class AdviceViewModelFactory (
    private val getAdviceUseCase: GetAdviceUseCase,
    private val translateAdviceUseCase: TranslateAdviceUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdviceViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AdviceViewModel(getAdviceUseCase,translateAdviceUseCase) as T
        }
        throw IllegalArgumentException("Unknow viewmodel class: ${modelClass.name}")
    }
}