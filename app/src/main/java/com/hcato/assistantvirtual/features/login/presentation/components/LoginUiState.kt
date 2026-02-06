package com.hcato.assistantvirtual.features.login.presentation.components

data class LoginUiState (
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)