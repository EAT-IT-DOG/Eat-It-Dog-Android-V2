package com.eatitdog.data.network.api

import com.eatitdog.data.network.response.data.SearchOfCategoryResponse
import com.eatitdog.data.network.response.data.SearchResponse
import com.eatitdog.data.network.url.DogUrl
import com.eatitdog.domain.model.search.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET(DogUrl.SEARCH_NAME)
    suspend fun getResultOfName(
        @Query("name") name: String
    ): SearchResult

    @GET(DogUrl.SEARCH)
    suspend fun getResult(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): List<SearchResponse>


    @GET(DogUrl.SEARCH_CATEGORY)
    suspend fun getResultOfCategory(
        @Query("type") type: String
    ): List<SearchOfCategoryResponse>

}