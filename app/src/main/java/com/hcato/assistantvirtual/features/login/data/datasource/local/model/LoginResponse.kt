package com.hcato.assistantvirtual.features.login.data.datasource.local.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class LoginRequestDto(
    val email: String,
    val password: String
)

data class LoginResponseDto(
    val token: String,
    val user: UserDto
)
data class UserDto(
    @SerializedName("user_id")
    val userId: Int,
    val name: String,
    val surnames: String,
    val email: String,
    val premium: Boolean,
    @SerialName("device_id")
    val deviceId: Int
)