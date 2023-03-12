package com.eatitdog.data.datasource

import com.eatitdog.data.base.BaseDataSource
import com.eatitdog.data.network.remote.SearchRemote
import com.eatitdog.data.network.response.data.SearchOfCategoryResponse
import com.eatitdog.data.network.response.data.SearchResponse
import com.eatitdog.domain.model.search.CategoryType
import javax.inject.Inject

class SearchDataSource @Inject constructor(
    override val remote: SearchRemote,
    override val cache: Any
) : BaseDataSource<SearchRemote, Any> {

    suspend fun getResult(page: Int, size: Int): List<SearchResponse> = remote.getResult(page, size)

    suspend fun getResultOfCategory(category: CategoryType): List<SearchOfCategoryResponse> = remote.getResultOfCategory(category)

}