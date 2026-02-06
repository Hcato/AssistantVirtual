package com.hcato.assistantvirtual.features.assistent.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcato.assistantvirtual.features.assistent.domain.usecases.GetAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.domain.usecases.TranslateAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.presentation.screens.AdviceUiState
import com.hcato.assistantvirtual.features.deleteuser.domain.usecase.DeleteUserUseCase
import com.hcato.assistantvirtual.features.updatestatus.domain.usecases.UpdateStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdviceViewModel(
    private val getAdviceUseCase: GetAdviceUseCase,
    private val translateAdviceUseCase: TranslateAdviceUseCase,
    private val updateStatusUseCase: UpdateStatusUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AdviceUiState())
    val state = _state.asStateFlow()

    private val _userDeleted = MutableStateFlow(false)
    val userDeleted = _userDeleted.asStateFlow()

    init {
        loadAdvice()
    }

    fun loadAdvice() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {
                val advice = getAdviceUseCase()
                val translated = translateAdviceUseCase(advice.advice)

                _state.update {
                    it.copy(
                        isLoading = false,
                        advice = advice.copy(advice = translated),
                        error = null
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error"
                    )
                }
            }
        }
    }

    fun updatePremium(value: Boolean) {
        viewModelScope.launch {
            _state.update { it.copy(isUpdatingPremium = true) }

            try {
                updateStatusUseCase(value)

                _state.update {
                    it.copy(
                        isPremium = value,
                        isUpdatingPremium = false
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isUpdatingPremium = false,
                        error = e.message
                    )
                }
            }
        }
    }
    fun deleteUser() {
        viewModelScope.launch {
            try {
                deleteUserUseCase()
                _userDeleted.value = true
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = e.message)
                }
            }
        }
    }
}


