package com.stac.domain.request.auth

data class JoinRequest(
    val email: String,
    val name: String,
    val password: String
)
