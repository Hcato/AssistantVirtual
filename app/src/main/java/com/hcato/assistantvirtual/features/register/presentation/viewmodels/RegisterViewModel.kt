package com.hcato.assistantvirtual.features.register.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcato.assistantvirtual.features.register.domain.usecases.RegisterUseCase
import com.hcato.assistantvirtual.features.register.presentation.components.RegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state.asStateFlow()


    fun register(
        name: String,
        surnames: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _state.value = RegisterUiState(isLoading = true)

            try {
                registerUseCase(
                    name = name,
                    surnames = surnames,
                    email = email,
                    password = password,
                    premium = false,
                    deviceId = 1
                )

                _state.value = RegisterUiState(isSuccess = true)
            } catch (e: Exception) {
                _state.value = RegisterUiState(
                    error = e.message ?: "Error al registrar"
                )
            }
        }
    }
}
