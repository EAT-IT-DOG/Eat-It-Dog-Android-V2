package com.stac.domain.request.auth

data class LoginRequest(
    val email: String,
    val password: String
)
