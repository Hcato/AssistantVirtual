package com.hcato.assistantvirtual.features.updatestatus.presentation.UpdateStatusViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hcato.assistantvirtual.features.updatestatus.domain.usecases.UpdateStatusUseCase

class UpdateStatusViewModelFactory (
    private val updateStatusViewModel: UpdateStatusUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateStatusViewModel::class.java)) {
            return UpdateStatusViewModel(updateStatusViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}