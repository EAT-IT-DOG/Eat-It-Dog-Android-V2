package com.eatitdog.data.network.remote

import android.util.Log
import com.eatitdog.data.base.remote.BaseRemote
import com.eatitdog.data.network.api.SearchApi
import com.eatitdog.data.network.response.data.SearchOfCategoryResponse
import com.eatitdog.data.network.response.data.SearchResponse
import com.eatitdog.domain.model.search.CategoryType
import com.eatitdog.domain.model.search.SearchResult
import javax.inject.Inject

class SearchRemote @Inject constructor(
    override val api: SearchApi
) : BaseRemote<SearchApi>() {

    suspend fun getResultOfName(name: String) : SearchResult {
        return api.getResultOfName(name)
    }

    suspend fun getResult(page: Int, size: Int): List<SearchResponse> {
        return api.getResult(page, size)
    }

    suspend fun getResultOfCategory(category: CategoryType): List<SearchOfCategoryResponse> {
        return api.getResultOfCategory(category.toString())
    }
}