package com.hcato.assistantvirtual.features.assistent.data.datasource.remote.mapper

import com.hcato.assistantvirtual.features.assistent.data.datasource.remote.model.AdviceDto
import com.hcato.assistantvirtual.features.assistent.domain.entities.Advice

fun AdviceDto.toDomain(): Advice {
    return Advice(
        id = this.id,
        advice = this.advice
    )
}