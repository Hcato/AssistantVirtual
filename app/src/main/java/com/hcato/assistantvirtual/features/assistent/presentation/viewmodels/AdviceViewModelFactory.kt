package com.hcato.assistantvirtual.features.assistent.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hcato.assistantvirtual.features.assistent.domain.usecases.GetAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.domain.usecases.TranslateAdviceUseCase
import com.hcato.assistantvirtual.features.deleteuser.domain.usecase.DeleteUserUseCase
import com.hcato.assistantvirtual.features.updatestatus.domain.usecases.UpdateStatusUseCase

class AdviceViewModelFactory (
    private val getAdviceUseCase: GetAdviceUseCase,
    private val translateAdviceUseCase: TranslateAdviceUseCase,
    private val updateStatusUseCase: UpdateStatusUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdviceViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return AdviceViewModel(getAdviceUseCase,translateAdviceUseCase, updateStatusUseCase, deleteUserUseCase) as T
        }
        throw IllegalArgumentException("Unknow viewmodel class: ${modelClass.name}")
    }
}