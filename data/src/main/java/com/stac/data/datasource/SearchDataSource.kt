package com.stac.data.datasource

import com.stac.data.base.BaseDataSource
import com.stac.data.network.remote.SearchRemote
import com.stac.data.network.response.data.SearchOfCategoryResponse
import com.stac.data.network.response.data.SearchResponse
import com.stac.domain.model.search.CategoryType
import com.stac.domain.model.search.SearchResult
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    override val remote: SearchRemote,
    override val cache: Any
) : BaseDataSource<SearchRemote, Any> {
    suspend fun getResultOfName(name: String): SearchResult = remote.getResultOfName(name)
    suspend fun getResultAll(page: Int, size: Int): List<SearchResponse> = remote.getResultAll(page, size)

    suspend fun getResultOfCategory(page: Int, type: CategoryType): List<SearchResult> = remote.getResultOfCategory(page, type)

    suspend fun getResult(keyword: String?, page: Int, type: CategoryType?): List<SearchResult> = remote.getResult(keyword, page, type)

}