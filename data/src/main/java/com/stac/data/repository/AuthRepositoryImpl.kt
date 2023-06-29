package com.stac.data.repository

import com.stac.data.database.entity.AccountEntity
import com.stac.data.datasource.AccountDataSource
import com.stac.data.datasource.AuthDataSource
import com.stac.data.datasource.SearchDataSource
import com.stac.data.datasource.TokenDataSource
import com.stac.data.mapper.toModel
import com.stac.domain.model.auth.User
import com.stac.domain.model.search.SearchResultByCategory
import com.stac.domain.model.search.CategoryType
import com.stac.domain.model.search.SearchResult
import com.stac.domain.repository.AuthRepository
import com.stac.domain.repository.SearchRepository
import com.stac.domain.request.auth.JoinRequest
import com.stac.domain.request.auth.LoginRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val accountDataSource: AccountDataSource,
    private val tokenDataSource: TokenDataSource
) : AuthRepository {
    override suspend fun join(params: JoinRequest) {
        return authDataSource.join(params)
    }

    override suspend fun login(params: LoginRequest) {
        authDataSource.login(params).also {
            accountDataSource.insertAccount(AccountEntity(params.email, params.password))
            tokenDataSource.insertToken(User(it.access_token, it.refresh_token))
        }
    }
}