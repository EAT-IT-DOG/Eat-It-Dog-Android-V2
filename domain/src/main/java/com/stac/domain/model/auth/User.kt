package com.stac.domain.model.auth

data class User(
    val access_token: String,
    val refresh_token: String
)
