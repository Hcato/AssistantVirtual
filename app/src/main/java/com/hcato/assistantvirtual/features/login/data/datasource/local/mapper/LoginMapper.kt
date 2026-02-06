package com.hcato.assistantvirtual.features.login.data.datasource.local.mapper

import com.hcato.assistantvirtual.features.login.data.datasource.local.model.UserDto
import com.hcato.assistantvirtual.features.login.domain.entities.Users

fun UserDto.toDomain() = Users(
    id = userId,
    name = name,
    surnames = surnames,
    email = email,
    isPremium = premium,
    deviceId = deviceId
)
