package com.hcato.assistantvirtual.features.assistent.presentation.screens

import com.hcato.assistantvirtual.features.assistent.domain.entities.Advice

data class AdviceUiState (
    val isLoading: Boolean = false,
    val advice: Advice? = null,
    val error: String? = null
)