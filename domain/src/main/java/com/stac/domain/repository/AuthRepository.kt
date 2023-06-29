package com.stac.domain.repository

import com.stac.domain.model.auth.User
import com.stac.domain.model.search.SearchResultByCategory
import com.stac.domain.model.search.CategoryType
import com.stac.domain.model.search.SearchResult
import com.stac.domain.request.auth.JoinRequest
import com.stac.domain.request.auth.LoginRequest
import com.stac.domain.usecases.auth.Join
import com.stac.domain.usecases.auth.Login

interface AuthRepository {
    suspend fun join(params: JoinRequest)
    suspend fun login(params: LoginRequest)
}