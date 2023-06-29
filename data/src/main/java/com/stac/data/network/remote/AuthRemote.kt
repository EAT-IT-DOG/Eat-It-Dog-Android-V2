package com.stac.data.network.remote

import com.stac.data.base.remote.BaseRemote
import com.stac.data.network.api.AuthApi
import com.stac.data.network.api.SearchApi
import com.stac.data.network.response.data.SearchOfCategoryResponse
import com.stac.data.network.response.data.SearchResponse
import com.stac.domain.model.auth.User
import com.stac.domain.model.search.CategoryType
import com.stac.domain.model.search.SearchResult
import com.stac.domain.request.auth.JoinRequest
import com.stac.domain.request.auth.LoginRequest
import com.stac.domain.request.auth.TokenRequest
import javax.inject.Inject

class AuthRemote @Inject constructor(
    override val api: AuthApi
) : BaseRemote<AuthApi>() {

    suspend fun login(params: LoginRequest) : User {
        return api.login(params)
    }

    suspend fun join(params: JoinRequest) {
        return api.join(params)
    }

    suspend fun getNewToken(token: String): String {
        return api.getNewToken(TokenRequest(token))
    }
}