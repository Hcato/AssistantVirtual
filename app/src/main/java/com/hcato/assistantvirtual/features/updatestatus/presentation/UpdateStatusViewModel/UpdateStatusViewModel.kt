package com.hcato.assistantvirtual.features.updatestatus.presentation.UpdateStatusViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcato.assistantvirtual.features.updatestatus.domain.usecases.UpdateStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UpdateStatusViewModel (
    private val useCase: UpdateStatusUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(false)
    val state = _state.asStateFlow()

    fun updateStatus(premium: Boolean) {
        viewModelScope.launch {
            try {
                useCase(premium)
                _state.value = true
            } catch (e: Exception) {
                Log.e("UPDATE_STATUS", e.message ?: "Error")
            }
        }
    }
}