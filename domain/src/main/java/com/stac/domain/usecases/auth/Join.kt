package com.stac.domain.usecases.auth

import com.stac.domain.base.UseCase
import com.stac.domain.model.search.CategoryType
import com.stac.domain.model.search.SearchResultByCategory
import com.stac.domain.repository.AuthRepository
import com.stac.domain.request.auth.JoinRequest
import com.stac.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Join @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<JoinRequest, Unit>() {


    override operator fun invoke(params: JoinRequest): Flow<Resource<Unit>> = execute {
        authRepository.join(params)
    }

}