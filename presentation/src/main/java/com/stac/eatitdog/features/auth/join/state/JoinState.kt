package com.stac.eatitdog.features.auth.join.state

data class JoinState(
    val isUpdate: Boolean = false,
    val result: Unit? = null,
    val error: String = ""
)
