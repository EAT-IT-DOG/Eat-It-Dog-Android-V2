package com.stac.data.datasource

import com.stac.data.base.BaseDataSource
import com.stac.data.network.remote.AuthRemote
import com.stac.data.network.remote.SearchRemote
import com.stac.data.network.response.data.SearchOfCategoryResponse
import com.stac.data.network.response.data.SearchResponse
import com.stac.domain.model.auth.User
import com.stac.domain.model.search.CategoryType
import com.stac.domain.model.search.SearchResult
import com.stac.domain.request.auth.JoinRequest
import com.stac.domain.request.auth.LoginRequest
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    override val remote: AuthRemote,
    override val cache: Any
) : BaseDataSource<AuthRemote, Any> {
    suspend fun login(params: LoginRequest): User = remote.login(params)
    suspend fun join(params: JoinRequest) = remote.join(params)

}