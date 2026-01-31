package com.hcato.assistantvirtual.features.assistent.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcato.assistantvirtual.features.assistent.domain.usecases.GetAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.domain.usecases.TranslateAdviceUseCase
import com.hcato.assistantvirtual.features.assistent.presentation.screens.AdviceUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdviceViewModel(
    private val getAdviceUseCase: GetAdviceUseCase,
    private val translateAdviceUseCase: TranslateAdviceUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AdviceUiState())
    val state = _state.asStateFlow()

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
}

