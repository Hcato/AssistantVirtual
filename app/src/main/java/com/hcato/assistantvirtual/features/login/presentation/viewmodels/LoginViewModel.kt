package com.hcato.assistantvirtual.features.login.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hcato.assistantvirtual.features.login.domain.usecases.LoginUseCase
import com.hcato.assistantvirtual.features.login.presentation.components.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel (
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginUiState(isLoading = true)
            try {
                loginUseCase(email, password)
                _state.value = LoginUiState(isSuccess = true)
            } catch (e: Exception) {
                _state.value = LoginUiState(error = e.message)
            }
        }
    }
}