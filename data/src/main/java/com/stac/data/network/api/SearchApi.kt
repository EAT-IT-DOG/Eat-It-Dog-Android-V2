package com.stac.data.network.api

import com.stac.data.network.response.data.SearchOfCategoryResponse
import com.stac.data.network.response.data.SearchResponse
import com.stac.data.network.url.DogUrl
import com.stac.domain.model.search.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET(DogUrl.SEARCH_NAME)
    suspend fun getResultOfName(
        @Query("name") name: String
    ): SearchResult

    @GET(DogUrl.SEARCH_ALL)
    suspend fun getResultAll(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): List<SearchResponse>

    @GET(DogUrl.SEARCH)
    suspend fun getResult(
        @Query("keyword") keyword: String?,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("type") type: String?
    ) : List<SearchResult>

}