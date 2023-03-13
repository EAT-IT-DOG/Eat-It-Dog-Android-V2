package com.eatitdog.domain.repository

import com.eatitdog.domain.model.search.SearchResultByCategory
import com.eatitdog.domain.model.search.CategoryType
import com.eatitdog.domain.model.search.SearchResult

interface SearchRepository {
    suspend fun getResultByName(name: String) : SearchResult

    suspend fun getResult(page: Int, size: Int) : List<SearchResultByCategory>

    suspend fun getResultByCategory(categoryType: CategoryType) : List<SearchResultByCategory>

}