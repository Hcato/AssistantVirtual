package com.hcato.assistantvirtual.features.updatestatus.di

import com.hcato.assistantvirtual.core.di.AppContainer
import com.hcato.assistantvirtual.features.updatestatus.domain.usecases.UpdateStatusUseCase
import com.hcato.assistantvirtual.features.updatestatus.presentation.UpdateStatusViewModel.UpdateStatusViewModelFactory

class UpdateStatusModule (
    private val appContainer: AppContainer
) {

    private fun provideUpdateStatusUseCase(): UpdateStatusUseCase {
        return UpdateStatusUseCase(appContainer.updateStatusRepository)
    }

    fun provideUpdateStatusViewModelFactory(): UpdateStatusViewModelFactory {
        return UpdateStatusViewModelFactory(
            provideUpdateStatusUseCase()
        )
    }
}