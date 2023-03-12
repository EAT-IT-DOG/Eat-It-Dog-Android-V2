package com.eatitdog.domain.repository

import com.eatitdog.domain.model.search.SearchResult
import com.eatitdog.domain.model.search.CategoryType

interface SearchRepository {

    suspend fun getResult(page: Int, size: Int) : List<SearchResult>

    suspend fun getResultByCategory(categoryType: CategoryType) : List<SearchResult>

}