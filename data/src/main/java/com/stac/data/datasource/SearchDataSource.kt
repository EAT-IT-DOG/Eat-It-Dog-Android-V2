package com.stac.data.datasource

import com.stac.data.base.BaseDataSource
import com.stac.data.network.remote.SearchRemote
import com.stac.data.network.response.data.SearchOfCategoryResponse
import com.stac.data.network.response.data.SearchResponse
import com.stac.domain.model.search.CategoryType
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    override val remote: SearchRemote,
    override val cache: Any
) : BaseDataSource<SearchRemote, Any> {
    suspend fun getResultOfName(name: String) = remote.getResultOfName(name)
    suspend fun getResult(page: Int, size: Int): List<SearchResponse> = remote.getResult(page, size)

    suspend fun getResultOfCategory(category: CategoryType): List<SearchOfCategoryResponse> = remote.getResultOfCategory(category)

}