package com.stac.data.network.api

import com.stac.data.network.response.data.SearchOfCategoryResponse
import com.stac.data.network.response.data.SearchResponse
import com.stac.data.network.url.DogUrl
import com.stac.domain.model.auth.User
import com.stac.domain.model.search.SearchResult
import com.stac.domain.request.auth.JoinRequest
import com.stac.domain.request.auth.LoginRequest
import com.stac.domain.request.auth.TokenRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST(DogUrl.LOGIN)
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): User

    @POST(DogUrl.JOIN)
    suspend fun join(
        @Body joinRequest: JoinRequest
    )

    @POST(DogUrl.TOKEN_REFRESH)
    suspend fun getNewToken(
        @Body tokenRequest: TokenRequest
    ): String


}