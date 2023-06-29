package com.stac.data.mapper

import com.stac.data.database.entity.TokenEntity
import com.stac.domain.model.auth.User

fun TokenEntity?.toModel(): User = User(
    access_token = this?.token ?: "",
    refresh_token = this?.refreshToken ?: ""
)

fun User.toEntity(): TokenEntity = TokenEntity(
    idx = 0,
    token = this.access_token,
    refreshToken = this.refresh_token
)
