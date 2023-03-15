package com.stac.domain.repository

import com.stac.domain.model.search.SearchResultByCategory
import com.stac.domain.model.search.CategoryType
import com.stac.domain.model.search.SearchResult

interface SearchRepository {
    suspend fun getResultByName(name: String) : SearchResult

    suspend fun getResult(page: Int, size: Int) : List<SearchResultByCategory>

    suspend fun getResultByCategory(categoryType: CategoryType) : List<SearchResultByCategory>

}