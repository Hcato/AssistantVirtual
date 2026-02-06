package com.hcato.assistantvirtual.features.register.data.datasource.local.model

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    val name: String,
    val surnames: String,
    val email: String,
    val password: String,
    val premium: Boolean,
    @SerializedName("device_id")
    val deviceId: Int
)