package com.stac.domain.usecases.auth

import com.stac.domain.base.UseCase
import com.stac.domain.model.auth.User
import com.stac.domain.model.search.CategoryType
import com.stac.domain.model.search.SearchResultByCategory
import com.stac.domain.repository.AuthRepository
import com.stac.domain.request.auth.LoginRequest
import com.stac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Login @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<LoginRequest, Unit>() {


    override operator fun invoke(params: LoginRequest): Flow<Resource<Unit>> = execute {
        authRepository.login(params)
    }
}