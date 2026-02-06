package com.hcato.assistantvirtual.features.register.presentation.components

data class RegisterUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)
