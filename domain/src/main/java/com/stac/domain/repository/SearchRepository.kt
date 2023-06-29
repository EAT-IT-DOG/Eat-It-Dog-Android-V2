package com.stac.domain.repository

import com.stac.domain.model.search.SearchResultByCategory
import com.stac.domain.model.search.CategoryType
import com.stac.domain.model.search.SearchResult

interface SearchRepository {
    suspend fun getResultByName(name: String) : SearchResult

    suspend fun getResultAll(page: Int, size: Int) : List<SearchResultByCategory>

    suspend fun getResultByCategory(page: Int, type: CategoryType) : List<SearchResultByCategory>

    suspend fun getResult(keyword: String?, page: Int ,type: CategoryType?) : List<SearchResultByCategory>
}