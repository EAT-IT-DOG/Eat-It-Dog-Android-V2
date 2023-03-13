package com.eatitdog.data.mapper

import com.eatitdog.data.network.response.data.SearchOfCategoryResponse
import com.eatitdog.data.network.response.data.SearchResponse
import com.eatitdog.domain.model.search.SearchResultByCategory

fun SearchOfCategoryResponse.toModel(category: String): SearchResultByCategory = SearchResultByCategory(
    name = this.name,
    category = category,
    grade = this.safeness
)

fun SearchResponse.toModel(): SearchResultByCategory = SearchResultByCategory(
    name = this.name,
    category = this.type,
    grade = this.safeness
)