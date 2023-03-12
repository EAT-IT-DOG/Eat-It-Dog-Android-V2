package com.eatitdog.data.mapper

import com.eatitdog.data.network.response.data.SearchOfCategoryResponse
import com.eatitdog.data.network.response.data.SearchResponse
import com.eatitdog.domain.model.search.SearchResult

fun SearchOfCategoryResponse.toModel(category: String): SearchResult = SearchResult(
    name = this.name,
    category = category,
    grade = this.safeness
)

fun SearchResponse.toModel(): SearchResult = SearchResult(
    name = this.name,
    category = this.type,
    grade = this.safeness
)