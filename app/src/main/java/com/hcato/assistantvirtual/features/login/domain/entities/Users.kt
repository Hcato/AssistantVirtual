package com.hcato.assistantvirtual.features.login.domain.entities

data class Users (
    val id: Int,
    val name: String,
    val surnames: String,
    val email: String,
    val isPremium: Boolean,
    val deviceId: Int
)