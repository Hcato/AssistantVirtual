package com.hcato.assistantvirtual.features.login.domain.entities

data class LoginResult(
    val token: String,
    val user: Users
)