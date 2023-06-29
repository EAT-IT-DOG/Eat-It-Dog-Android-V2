package com.stac.eatitdog.features.auth.login.state

data class LoginState(
    val isUpdate: Boolean = false,
    val result: Unit? = null,
    val error: String = ""
)
