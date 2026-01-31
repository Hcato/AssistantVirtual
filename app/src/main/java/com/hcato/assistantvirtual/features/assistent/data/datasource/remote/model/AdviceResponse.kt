package com.hcato.assistantvirtual.features.assistent.data.datasource.remote.model

data class AdviceResponse(
    val slip: AdviceDto
)
data class AdviceDto(
    val id: Int,
    val advice: String
)