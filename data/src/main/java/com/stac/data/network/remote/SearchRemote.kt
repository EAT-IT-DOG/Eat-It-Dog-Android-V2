package com.stac.data.network.remote

import com.stac.data.base.remote.BaseRemote
import com.stac.data.network.api.SearchApi
import com.stac.data.network.response.data.SearchOfCategoryResponse
import com.stac.data.network.response.data.SearchResponse
import com.stac.domain.model.search.CategoryType
import com.stac.domain.model.search.SearchResult
import javax.inject.Inject

class SearchRemote @Inject constructor(
    override val api: SearchApi
) : BaseRemote<SearchApi>() {

    suspend fun getResultOfName(name: String) : SearchResult {
        return api.getResultOfName(name)
    }

    suspend fun getResultAll(page: Int, size: Int): List<SearchResponse> {
        return api.getResultAll(page, size)
    }

    suspend fun getResultOfCategory(page: Int, type: CategoryType): List<SearchResult> {
        return api.getResult("", page, 10, type.toString())
    }

    suspend fun getResult(keyword: String?, page: Int, type: CategoryType?) : List<SearchResult> {
        if(type == null) {
            return api.getResult(keyword, page, 10, "")
        }
        return api.getResult(keyword, page, 10, type.toString())
    }
}